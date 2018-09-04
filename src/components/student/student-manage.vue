<template>
    <div class="student-manage">
        <el-row > 
            <el-col :span="10" >
             <div style="height:25%;">
               <add-class-popup></add-class-popup> <!--这是新增班级-->
               <search-box2></search-box2>  <!--这是搜索框-->
            </div> 
               <class-table></class-table>         <!--这是教师表格-->
             
             </el-col> 
             <el-col :span="14">
               <div style="height:30%">
                <class-name></class-name> <!--班级名字-->
                <add-student-popup></add-student-popup>  <!--新增学生-->
                <import-in-batch></import-in-batch> <!--批量导入-->
                <download-template></download-template>  <!--下载模板按钮-->
                <search-box></search-box>  <!--搜索框-->
                </div> 
                <class-student-table></class-student-table> <!--学生表格-->

                
                
                
             </el-col>
        </el-row>

        
     
       
    </div>
</template>

<script>
import AddClassPopup from  './add-class-popup'  /* 这是新增班级*/ 
import ClassTable from './class-table'   /* 这是教师表格*/ 
import AddStudentPopup from './add-student-popup'  /* 这是新增学生*/ 
import ClassStudentTable from './class-student-table'   /* 这是学生表格*/ 
import importInBatch from './import-in-batch'  /* 这是批量导入*/ 
import DownloadTemplate from './download-template'  /* 这是下载模板*/ 
import SearchBox from './search-box'   /* 这是搜索框*/ 
import ClassName from './class-name'     /* 这是新增班级*/ 
import SearchBox2 from './search-box2' /* 这是搜索框*/
export default {
    components:{
        AddClassPopup, 
        ClassTable,
        AddStudentPopup,
        ClassStudentTable,
        importInBatch,
        DownloadTemplate,
        SearchBox,
        ClassName,
        SearchBox2,
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


</style>