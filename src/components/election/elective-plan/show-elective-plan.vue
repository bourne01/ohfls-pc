<template>
   <div  class="wrapper"> 
        <div class="add-plan" @click="goAddPlan">
            <i class="el-icon-plus"></i>
            <span>增加计划</span>
        </div>        
         <term-plan 
            class="elective-plan" 
            ></term-plan>
        <div class="show-plan">查看<br/>全部</div>           
    </div>   
</template>

<script>
import TermPlan from './term-plan'
export default {
   components:{
       TermPlan
   },
    data(){
        return{            
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
   .wrapper{
        width: 100%;
        background-color:#fff;
        padding:30px 40px;
        box-sizing: border-box;
   }
   .wrapper::after{
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
    }
    .elective-plan{
            float: left;            
        }
</style>