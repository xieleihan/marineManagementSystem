<template>

  <a-layout>
    <a-layout-content
        :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
    >
      <p>
        <a-button type="primary" @click="add()">
          新增
        </a-button>
      </p>
      <a-table
          :columns="columns"
          :row-key="record => record.id"
          :data-source="level1"
          :pagination="false"
          :loading="loading"
          @change="handleTableChange"
      >
        <template #cover="{ text: cover }">
          <img v-if="cover" :src="cover" alt="avatar" style="width: 20%;height: 20%"/>
        </template>
        <template v-slot:action="{ text, record }">
          <a-space size="small">
            <a-button type="primary" @click="edit(record)">
              编辑
            </a-button>
            <a-popconfirm
                title="删除不可恢复，确认删除？"
                ok-text="是"
                cancel-text="否"
                @confirm="handleDelete(record.id)"
            >
              <a-button type="danger" >
                删除
              </a-button>
            </a-popconfirm>

          </a-space>
        </template>
      </a-table>

    </a-layout-content>
  </a-layout>
  <a-modal
      title="海洋生物表单"
      v-model:visible="modalVisible"
      :confirm-loading="modalLoading"
      @ok="handleModalOk"
  >
    <a-form :model="category" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">






      <a-form-item label="名称">
        <a-input v-model:value="category.name" />
      </a-form-item>
      <a-form-item label="父分类">
        <a-select
            v-model:value="category.parent"
            ref="select"
        >
          <a-select-option value="0">
            无
          </a-select-option>
          <a-select-option v-for="c in level1" :key="c.id" :value="c.id" :disabled="category.id ===c.id">
            {{c.name}}
          </a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item label="顺序">
        <a-input v-model:value="category.sort" />
      </a-form-item>

    </a-form>
  </a-modal>
</template>
<script lang="ts">
import { defineComponent ,onMounted,ref} from 'vue';
import axios from 'axios'
import { message } from 'ant-design-vue';
import { Tool } from '@/utils/Tool';




export default defineComponent({
  name: 'Admincategory',
  components: {

  },
  setup(){
    const categorys = ref();
    const pagination = ref({
      current: 1,
      pageSize: 5,
      total: 0
    });
    const loading = ref(false);
    const columns = [

      {
        title: '名称',
        dataIndex: 'name'
      },
      {
        title: '父分类',
        dataIndex: 'parent'
      },
      {
        title: '顺序',
        dataIndex: 'sort'
      },

      {
        title: 'Action',
        key: 'action',
        slots: { customRender: 'action' }
      }
    ];

    /*
    * 数据查询
    * */


    // -------- 表单 ---------
    let fileName = "";
    const category = ref()
    const modalVisible = ref(false);
    const modalLoading = ref(false);
    const handleModalOk = () => {
      modalLoading.value = true;

      //判断有没有图片
      if(fileName!=""&&fileName!=null){
        //赋值
        category.value.cover = "/image/"+fileName;
      }


      axios.post("/category/save",category.value).then((resp)=>{
        //获取数据
        const data = resp.data
        if(data.success){
          modalVisible.value = false;
          modalLoading.value = false;
          handleQuery()
        }else {
          message.error("修改失败")
        }
      })

    };










//编辑
    const edit = (record:any)=>{
      category.value = record;
      modalVisible.value =true;
    }
//新增
    const add = ()=>{
      category.value ={};
      modalVisible.value =true;
    }
    const handleDelete = (id:number) => {
      axios.get("/category/remove",{
        params:{
          id:id
        }
      }).then((resp)=>{
        if(resp.data.success){
          handleQuery()
        }else {
          message.error("删除失败！")

        }
      })
    }
//数据查询
    const level1 = ref();
    
    const  handleQuery = ()=>{
      loading.value = true;
      axios.get("/category/allList").then((resp)=>{
        loading.value = false;
        const  data = resp.data;


        if(data.success){
          //转成树形结构
          console.log("原始数组",data.content)
          level1.value = []
          level1.value = Tool.array2Tree(data.content,0)
          console.log("树形结构",level1.value)
        }else {
          message.error(data.message)
        }

      });
    };
    onMounted(()=>{
      handleQuery();
    })
    return {
      categorys,
      pagination,
      columns,
      loading,



      edit,
      category,
      handleModalOk,
      modalVisible,
      modalLoading,
      add,
      handleDelete,
      level1,

    }
  }
});
</script>

