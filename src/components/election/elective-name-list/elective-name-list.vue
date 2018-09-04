<template>
    <div class="elective-name-list">
        <div class="header">
            <add-elective-student></add-elective-student>
            <prior-student></prior-student>
            <elective-time></elective-time>
            <form action="#" class="search-area">               
                <input type="text" class="search-ID" placeholder="查询学号">
                <input type="text" placeholder="查询姓名" class="search-name">
                <button class="search-button" type="submit"></button>   
            </form>
        </div>
        <student-table></student-table>
    </div>
</template>


<script>
import addElectiveStudent from './add-elective-student.vue'
import studentTable from './student-table.vue'
import electiveTime from './elective-time.vue'
import priorStudent from './prior-student.vue'
export default {
    components:{
        addElectiveStudent,
        studentTable,
        electiveTime,
        priorStudent
    },
}
</script>

<style scoped>
    .header{
        width: 100%;
        height: 45px;
        background-color: white;
        padding-top: 15px;
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
    .elective-name-list th{       /*  表头字体居中 */
      text-align: center;
      background-color:#E9EEF3;
      color: #1a1a1f;
    }
</style>



