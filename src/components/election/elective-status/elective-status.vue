<template>
    <div class="elective-status">
        <div class="header">    
            <export-elective-students></export-elective-students>
             <div><el-select v-model="selectedGrade" filterable placeholder=全部年级 size="small">
                    <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                    </el-option>
                </el-select>
                 <el-select v-model="selectedGrade" filterable placeholder="全部班级" size="small">
                    <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                    </el-option>
                 </el-select>
                </div>
            <form action="#" class="search-area">
                <input type="text" class="search-ID" placeholder="查询课程">
                <input type="text" placeholder="查询姓名" class="search-name">
                <button class="search-button" type="submit"></button>   
            </form>
        </div>
          <elective-status-table></elective-status-table>
    </div>
</template>

<script>
import exportElectiveStudents from './export-elective-students.vue'
import electiveStatusTable from './elective-status-table.vue'
export default {
    components:{
        exportElectiveStudents,
        electiveStatusTable
    },
    data(){
        return{
            selectedGrade:'',
            options:[{}]
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
    .elective-status{
        width: 100%;
        background-color: #fff;
    }
    .header{
        width: 100%;
        height: 45px;
        background-color: white;
        padding-top: 15px;
    }
    .el-select{
        height: 30px;
        width: 10%;
        margin-left: 2%;
        float: left;
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
      background: url(../../../assets/search-o.png) no-repeat;
      border-radius: 0 4px 4px 0;
      background-position: 48% 50%;

    }
    .search-ID{         /*搜索学号文框的样式*/
        float: left;
        background-color: #f6f9fb;
        border: 1px solid #e5e9ec;
        border-radius: 4px;
        height: 26px;
        margin-right: 10px;
        padding-left: 4.5%;
    }     
</style>

<style>
    .elective-status th{       /*  表头字体居中 */
    text-align: center;
    background-color:#E9EEF3;
    color: #1a1a1f;
    }
</style>
