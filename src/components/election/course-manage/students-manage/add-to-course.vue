<template>
    <div class="manage-list">
        <span class="student-num">5/50</span><br>
        <button class="manage-list-button" @click="isShow=true">管理名单</button>              <!--选课管理里面的管理名单按钮-->
            <el-dialog
                title="管理名单"
                :visible.sync="isShow"    
                width="80%"
                top="10vh"
                >                                                                                   <!--管理名单弹窗-->
                <el-dialog
                    title="管理名单"
                >
                <add-elective-student></add-elective-student>
                </el-dialog>
                <div class="manage-header">                                          <!--管理名单的头部部分-->
                   <add-elective-student></add-elective-student>        <!--添加学生组件-->
                    <el-button class="export-list-button">导出选课名单</el-button>      
                    <el-button class="check-excellent">查看优秀学生优先选择</el-button>
                        <form action="#" class="search-area">
                                <input type="text" class="search-course" placeholder="查询课程">
                                <input type="text" placeholder="查询姓名" class="search-name">
                                <button class="search-button" type="submit"></button>           <!--搜索提交按钮-->
                        </form>
                </div>
                <div class="manage-table">
                    <student-table-in-course :course-id='courseId'></student-table-in-course>                     <!--管理名单的具体表格组件-->
                </div>
        </el-dialog>
    </div>
</template>

<script> 
import studentTableInCourse from './student-table-in-course.vue'
import addElectiveStudent from '../../elective-name-list/add-elective-student.vue'
export default {
    props:['course-id'],
    components:{
        studentTableInCourse,
        addElectiveStudent
    },
    data(){
        return{
            isShow: false,         
        }   
    },
    methods:{
       close(){
           
    },
    },
    created(){

    },
    mounted(){
      
    },
    }
    

</script>

<style scoped>
    .manage-list-button{            /*打开管理名单的按钮*/
        height: 25px;
        width: 78px;
        border-radius: 2px;
        border: 1px solid #5b9bfd;
        background-color: transparent;
        color: #5b9bfd;
        cursor: pointer;
        margin-top:3px;
    }
   
    .manage-header{                 /*管理命大的头部样式**/
        height: 60px;
        width: 100%;
        padding-top:15px;
    }
    .student-num{
        color: #5b9bfd;
    }
    .export-list-button{        /*导出学生名单的按钮*/
        float: left;    
        background: url(../../../../assets/export.png) no-repeat;
        background-position: 12% 38%;
        height: 30px;
        width: 10%;
        margin-left: 3.65%;
        border-radius: 2px;
        color:rgb(91, 155, 253);
        padding-left: 3%;
        line-height: 32%;
        font-size: 'MicrosoftYaHei';
        font-size: 14px;
    }
    .check-excellent{           /*查看优先学生的按钮*/
        float: left;    
        background-position: 15% 38%;
        height: 30px;
        width: 12%;
        margin-left: 3.65%;
        border-radius: 2px;
        line-height: 32%;
        font-size: 'MicrosoftYaHei';
        font-size: 12px;
        color: black;
    }
    .search-area{                          /*整个搜索区域的样式*/
      float: right;
      width: 30%;
      margin-right: 8%;
    }
    .search-name{           /*搜索名字的文本框样式*/
       background-color: #f6f9fb;
       border: 1px solid #e5e9ec;
       border-radius: 4px 0 0 4px;
       float: left;
       width: 35%;
       height: 26px;
       padding-left: 4.5%;
      
      
    }
    .search-button{         /*搜索按钮的样式*/
      height: 30px;
      width: 9%;
      border: 1px solid #e5e9ec;
      cursor: pointer;
      display: block;
      background: url(../../../../assets/search-o.png) no-repeat;
      border-radius: 0 4px 4px 0;
      background-position: 48% 50%;

    }
    .search-course{         /*搜索课程文框的样式*/
        float: left;
        background-color: #f6f9fb;
        border: 1px solid #e5e9ec;
        border-radius: 4px;
        height: 26px;
        margin-right: 10px;
        padding-left: 4.5%;
    } 
   
    .footer{            /*底部样式*/
        height: 60px;
        width: 100%;
        background-color:rgb(228, 230, 231);
        padding-top: 15px;
    }
    .select-all{            /*全选样式*/
        float: left;
        margin-left: 1.5%
  }
    .footer-button{     /*底部按钮的整体样式*/
        float: left;
        width: 88px;
        height: 28px;
        line-height: 32%;
        font-size: 12px;
    }
  .el-pagination{
    float: right;
  }
</style>



<style>
 .manage-list .el-dialog  {    /*设置弹框整体的样式*/
   max-height:745px;
   
  
 }                                                               
 .manage-list .el-dialog__header{             /*设置弹框的头部*/
   background-color :#0d714a;
   height: 50px;
   color:#707079; 
   margin:0px;
 }
  .manage-list  .el-dialog__header .el-dialog__title{       /*设置头部的标题样式*/ 
    color:white;
    float: left;
    line-height:10px;
    margin-left:20px;
    font-size:16px;
    font-family:'MicrosoftYaHei';
 }
 .manage-list .el-dialog__headerbtn .el-dialog__close{      /*设置头部关闭的样式*/
   color:white;
   font-size:24px;
   margin-top:-30px;
 }
 
 .manage-list .el-dialog__body {                        /*设置弹框body内的内填充为0*/
    padding:0;  
    width:100%;
}
.course-manage-table .el-pagination.is-background .el-pager li:not(.disabled).active {   
      background-color:rgb(13,113,74);
    }
 
</style>
