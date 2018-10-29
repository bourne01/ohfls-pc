<template>
   <div  class="plan-wrapper"> 
        <div class="add-plan" @click="goAddPlan">
            <i class="el-icon-plus"></i>
            <span>增加计划</span>
        </div>        
         <term-plan 
            class="elective-plan" 
            ></term-plan>
        <div class="show-plan" @click="isShow=true">查看<br/>全部</div>
        <el-dialog
            title="选课计划管理"
            :visible.sync="isShow"    
            width="1000px"
            top="10vh"
            >                                                                                  
            <div class="manage-table">
                <elective-plan-table></elective-plan-table>
            </div>
        </el-dialog>           
    </div>   
</template>

<script>
import TermPlan from './term-plan'
import ElectivePlanTable from './elective-plan-table'
export default {
   components:{
       TermPlan,
       ElectivePlanTable
   },
    data(){
        return{
            isShow:false            
        }   
    },
    methods:{
        /**@function 跳转到新增课程页面 */
        goAddPlan(){
            this.$router.push('/home/add-plan');
        },
    },
}
</script>

<style scoped>
   .plan-wrapper{
        width: 100%;
        background-color:#fff;
        padding:30px 40px;
        box-sizing: border-box;
   }
   .plan-wrapper::after{
        content:'';
        display:block;
        height:0;
        clear:both;
   }
    .add-plan{        
        height: 106px;
        width:206px;
        background-color: white;
        border: 1px dashed #d9d9d9;
        color: #0d714a;
        font-size: 16px;
        border-radius: 4px;
        float: left;;
        display:flex;
        justify-content: center;
        flex-direction: column;
        margin-right:50px;
    }
    i.el-icon-plus{
        font-size:36px;
    }
    .show-plan{
        border: 1px dashed #d9d9d9;
        height: 96px;
        width: 176px;
        float: right;
        box-sizing: border-box;
        padding-top: 26px;
        line-height: 135%;
        font-size: 'MicrosoftYaHei';
        color: #b5b5c0;
        border-radius: 4px;
        cursor: pointer;
    }
    .elective-plan{
            float: left;            
        }
</style>

<style>
 .plan-wrapper .el-dialog  {    /*设置弹框整体的样式*/
   max-height:745px;
   
  
 }                                                               
 .plan-wrapper .el-dialog__header{             /*设置弹框的头部*/
   background-color :#0d714a;
   height: 20px;
   color:#707079; 
   margin:0px;
 }
  .plan-wrapper  .el-dialog__header .el-dialog__title{       /*设置头部的标题样式*/ 
    color:white;
    float: left;
    line-height:10px;
    margin-left:20px;
    font-size:16px;
    font-family:'MicrosoftYaHei';
 }
 .plan-wrapper .el-dialog__headerbtn .el-dialog__close{      /*设置头部关闭的样式*/
   color:white;
   font-size:24px;
   margin-top:-30px;
 }
 
 .plan-wrapper .el-dialog__body {                        /*设置弹框body内的内填充为0*/
    padding:0;  
    width:100%;
}
.course-manage-table .el-pagination.is-background .el-pager li:not(.disabled).active {   
      background-color:rgb(13,113,74);
    }
 
</style>