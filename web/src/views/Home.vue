<template>
  <div class="home">
    <a-layout-content style="padding: 0 50px">
      <a-layout style="padding: 24px 0; background: #fff">
        <a-layout-sider width="200" style="background: #fff">
          <a-menu
              mode="inline"
              :style="{ heighwt: '100%', borderRight: 0 }"
              @click="handleClick"
          >
            <a-menu-item key="welcome">
              <MailOutlined />
              <span>欢迎</span>
            </a-menu-item>
            <a-sub-menu v-for="item in level1" :key="item.id" >
              <template v-slot:title>
                <span><user-outlined />{{item.name}}</span>
              </template>
              <a-menu-item v-for="child in item.children" :key="child.id">
                <MailOutlined /><span>{{child.name}}</span>
              </a-menu-item>
            </a-sub-menu>
          </a-menu>
        </a-layout-sider>
        <a-layout-content :style="{ padding: '0 24px', minHeight: '280px' }">

          <div class="welcome" v-show="isShowWelcome">
            <h1>欢迎使用海洋生物知识库</h1>
          </div>
          <a-list v-show="!isShowWelcome" item-layout="vertical" size="large":data-source="ebooks"  :grid="{gutter:20,column:3}">

            <template #renderItem="{ item }">
              <a-list-item key="item.name">
                <template #actions>
  <span v-for="{ type, text } in actions" :key="type">
    <component :is="type" style="margin-right: 8px" />
    {{ text }}
  </span>
                </template>

                <a-list-item-meta :description="item.description">
                  <template #title>
                    <a :href="item.href">{{ item.name }}</a>
                  </template>
                  <template #avatar><a-avatar :src="item.cover" /></template>
                </a-list-item-meta>

              </a-list-item>
            </template>
          </a-list>
        </a-layout-content>
      </a-layout>
    </a-layout-content>
  </div>
</template>

<script lang="ts">
import { defineComponent ,onMounted,ref,reactive,toRef} from 'vue';
import axios from 'axios'
import {Tool} from "@/utils/Tool";
import {message} from "ant-design-vue";



export default defineComponent({
  name: 'HomeView',
  // components: {
  //
  // },
  setup(){
    console.log("setup");
    const  ebooks = ref();
    //数据查询
    const level1 = ref();
    let categorys:any;

    const  handleQueryCategory = ()=>{

      axios.get("/category/allList").then((resp)=>{

        const  data = resp.data;
        categorys = data.content;


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

    const isShowWelcome = ref(true);
    let category2Id = 0;
    const handleClick = (value:any) => {
      if(value.key ==='welcome'){
        isShowWelcome.value =true
      }else {
        category2Id = value.key
        isShowWelcome.value = false;
        handleQueryEbook();
      }
      
    }
    const handleQueryEbook = () => {
      axios.get("/ebook/getEbookListByPage",{
        params:{
          page:1,
          size:1000,
          category2Id:category2Id
        }
      }).then(function (resp){
        console.log(resp);
        const data = resp.data.content.list;
        ebooks.value = data;

      });
      
    }


    onMounted(()=>{
      handleQueryCategory();

    })
    return {
      ebooks,
      level1,
      handleClick,
      isShowWelcome,


    }
  }
});
</script>

<style scoped>
.ant-avatar {
  width: 50px;
  height: 50px;
  line-height: 50px;
  border-radius: 8%;
  margin: 5px 0;
}
</style>