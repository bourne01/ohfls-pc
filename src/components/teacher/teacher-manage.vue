<template>
    <div class="teacher-manage">
       <div style="height:10%">
        <add-teacher></add-teacher>  <!--这是添加教师按钮-->
        <import-in-batch></import-in-batch> <!--这是批量导入-->
         <download-template></download-template><!--这是下载模板-->
        <search-box></search-box> <!--这是搜索框-->
    </div> 
         <teacher-table></teacher-table> <!--这是表格主体-->
        
  
    </div>
        
</template>

<script>
import AddTeacher from  './add-teacher.vue'
import TeacherTable from  './teacher-table.vue'
import ImportInBatch from './import-in-batch'
import DownloadTemplate from './download-template'
import SearchBox from './search-box'

export default {
    components:{
        AddTeacher,
        TeacherTable,
        DownloadTemplate,
        ImportInBatch,
        SearchBox,
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
.teacher-manage{ /*表格背景色 */
  background: white; 
  width: 100%;
  height: 100%;
}



</style>