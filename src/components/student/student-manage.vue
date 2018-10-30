<template>
    <div class="student-manage">
        <el-row :gutter="50"> 
            <el-col :span="9" >
               <class-toolbar></class-toolbar>
               <class-table></class-table>         <!--这是教师表格-->
             </el-col> 
             <el-col :span="15">
               <!-- <div style="height:30%"> -->
                <!-- <class-name></class-name> --> <!--班级名字-->
                <!-- <add-student-popup></add-student-popup> -->  <!--新增学生-->
                <!-- <import-in-batch></import-in-batch> --> <!--批量导入-->
               <!--  <download-template></download-template>  --> <!--下载模板按钮-->
                <!-- <search-box></search-box> -->  <!--搜索框-->
                <!-- </div>  -->
                <student-toolbar></student-toolbar>
                <class-student-table></class-student-table> <!--学生表格-->
             </el-col>
        </el-row>

        
     
       
    </div>
</template>

<script>
import ClassTable from './class-table'   /* 这是教师表格*/ 
import ClassStudentTable from './class-student-table'   /* 这是学生表格*/ 
import importInBatch from './import-in-batch'  /* 这是批量导入*/ 
import DownloadTemplate from './download-template'  /* 这是下载模板*/ 
import ClassName from './class-name'     /* 这是新增班级*/ 
import ClassToolbar from './class-toolbar'
import StudentToolbar from './student-toolbar'
export default {
    components:{
        ClassTable,
        ClassStudentTable,
        importInBatch,
        DownloadTemplate,
        ClassName,
        ClassToolbar,
        StudentToolbar
    },
    data(){
        return{

        }   
    },
    methods:{

    },
    created(){

    },
    mounted(){

    }
}
</script>

<style scoped>
    body,html{ /*表格背景色 */
    height: 100%;
    }
    .student-manage{ /*表格背景色 */
    background: white; 
    width: 100%;
    height: 100%;
    }
    .el-row{
        margin-left:0!important;
        margin-right:0!important;
    }
    .el-col-9{
        padding-left:0!important;
    }
    .el-col-15{
        padding-right:0!important;
    }
</style>