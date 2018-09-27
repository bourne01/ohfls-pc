<template>
    <div>
        <transition name="fade" mode="out-in">
            <router-view></router-view>
            </transition>   
        <show-elecitve-plan></show-elecitve-plan>
         <div class="my-tabs">
            <div class="tab-header">
                <div class="left">
                    <span 
                        class="tab-title" 
                        @click="tabIndex=1"
                        :class="{active:tabIndex===1}">
                        课程管理
                        <div class="active-bar1"></div>
                    </span>
                    <span 
                        class="tab-title" 
                        @click="tabIndex=2"
                        :class="{active:tabIndex===2}">
                        学生参选名单
                        <div class="active-bar2"></div>
                    </span>
                    <span 
                        class="tab-title" 
                        @click="tabIndex=3"
                        :class="{active:tabIndex===3}">
                        学生选课状态
                        <div class="active-bar2"></div>
                    </span>
                </div>
                <div class="right" @click="gotoEditPlan">
                    <i class="el-icon-edit"></i>编辑/修改计划
                </div>
            </div>
            <div v-if="tabIndex===1">
                <course-manage></course-manage>
            </div>
            <div v-if="tabIndex===2">
                <elective-name-list></elective-name-list>
            </div>
            <div v-if="tabIndex===3">
                <elective-status></elective-status>
            </div>
         </div>
    </div>
</template>

<script>
import ShowElecitvePlan from './elective-plan/show-elective-plan.vue'
import electiveNameList from './elective-name-list/elective-name-list.vue'
import courseManage from './course-manage/course-manage.vue'
import electiveStatus from './elective-status/elective-status.vue'
import { mapState } from 'vuex';
export default {
 components:{
        ShowElecitvePlan,
        electiveNameList,
        courseManage,
        electiveStatus
    },
    data(){
        return{
              activeName: 'first',
              tabIndex:1,//默认激活课程管理选项卡
        }   
    },
    computed:{
        ...mapState('election',{
            planId:state => state.currentPlanId,
        })
    },
    methods:{
         handleClick(tab, event) {
        console.log(tab, event);
      },
      /**@function  跳转到编辑/修改计划页面*/
      gotoEditPlan(){
          /**
           * xkpId的参数应该来自组件 temp-plan
           */
          this.$router.push({name:'AddElectivePlan',query:{planId:this.planId}})
      },
    },
    created(){

    },
    mounted(){

    },

}

</script>

<style scoped>
   .my-tabs{
       width:100%;
   }
   .tab-header{
       padding:20px 0;
       background-color:#fff;
       height:1.5em;
       margin-bottom:15px;
   }
   .my-tabs .left{
       float:left;
   }
   .my-tabs .right{
       float:right;
       margin-right:40px;
       font-size: 14px;
       color:#5b9bfd;
       cursor: pointer;
   }
   i.el-icon-edit{
       margin-right:5px;
   }
   .tab-title{
       height:1.5em;
       border-right:1px solid #e4e6e7;
       padding:0 80px;
       display: inline-block;      
       font-size: 20px;
       color: #707079;
       position: relative;
       cursor: pointer;
   }
   .active{
       color: #0d714a;
   }
   .active-bar1{
       display: none;
       width:110px;     
       height:4px;
       position:absolute;
       left:65px;
       bottom:-12px;
       background-color:#0d714a;
   }
   .active-bar2{
       display: none;
       width:150px;     
       height:4px;
       position:absolute;
       left:65px;
       bottom:-12px;
       background-color:#0d714a;
   }
   .active .active-bar1,
   .active .active-bar2
   {
       display:inline-block;
   }
</style>