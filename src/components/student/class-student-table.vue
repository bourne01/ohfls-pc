<template>
   <div class="class-student-table">    <!--这是右边新增学生表格-->
    <el-table
    ref="multipleTable"
    :data="classTable"
    tooltip-effect="dark"
    style="width: 95%;margin-left:48px"
    border
    max-height="650"
    @selection-change="handleSelectionChange">
    <el-table-column
      type="selection"
      width="55">
    </el-table-column>
    <el-table-column
      prop="stuNO"
      label="学号"
      width="120">
    </el-table-column>
    <el-table-column
      prop="stuName"
      label="姓名"
      width="100">
    </el-table-column>
    <el-table-column
      prop="stuSex"
      label="性别"
      show-overflow-tooltip
      width="60">
    </el-table-column>
    <el-table-column
      prop="credit"
      label="学分"
      width="80">
     </el-table-column>
     <el-table-column
      prop="stuTo"
      label="去向"
      width="100"
      :filters="[{ text: '休学', value: '休学' }, { text: '退学', value: '退学' }, { text: '历史', value: '历史' }]"
      :filter-method="filterTag1"
      filter-placement="bottom-end">
    </el-table-column>
    <el-table-column
      prop="stuState"
      label="状态"
      width="100"
      :filters="[{ text: '正常', value: '正常' }, { text: '历史', value: '历史' }, { text: '关闭', value: '关闭' }]"
      :filter-method="filterTag2"
      filter-placement="bottom-end">
     
    </el-table-column>    
    <el-table-column    
      prop="Jump"
      label="跳转"
      width="125">     <!--这是功能按钮--> 
      <template slot-scope="scope">
        <el-button type="text" >跳转至学生端</el-button>
      </template>
    </el-table-column>
    <el-table-column    
      prop="operation"
      label="操作">     <!--这是功能按钮--> 
      <template slot-scope="scope">
        <el-button type="text" >编辑</el-button>
        <el-button type="text" size="small" @click="dialogVisible2 = true">重设密码</el-button>
        <el-button type="text" >删除</el-button>
      </template>
    </el-table-column>
  </el-table>
  <div class="postion1"> <!--这是按钮位置-->
      <el-button @click="toggleSelection(tableData)">全选</el-button>
      <el-button @click="toggleSelection()">批量删除</el-button>
  </div> 
  <div class="block"><!--这是翻页功能-->
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page.sync="currentPage1"
      :page-size="10"
      layout="total, prev, pager, next"
      :total="100">
    </el-pagination>
  </div>
   </div>
</template>
<style scoped>

</style>
<script>
import {mapActions,mapState} from 'vuex'
  export default {
    data() {
      return {
         dialogVisible2: false, /*重设密码dialog事件*/
        multipleSelection: [],
        currentPage1:1,
      }
    },

    methods: {
      toggleSelection(rows) {
        if (rows) {
          rows.forEach(row => {
            this.$refs.multipleTable.toggleRowSelection(row);
          });
        } else {
          this.$refs.multipleTable.clearSelection();
        }
      },
      filterTag1(value, row) {     /* 去向筛选方法*/
        return row.where === value;
      },
        filterTag2(value, row) {   /* 状态筛选方法*/
        return row.state === value;
      },
      handleSelectionChange(val) {   
        this.multipleSelection = val;
      },
      handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
      },
      ...mapActions('student',['getClassTable'])
    },
    computed:
    {
      ...mapState('student',{
        classTable:state => state.classTable
      })
    },
    mounted(){
      let url = 'claStu!query2.action';
      let params ={start:0,limit:8};
      this.getClassTable({url,params})
    }
  }
</script>
<style scoped>
.postion1{    /*全选按钮和批量删除按钮位置*/
 margin-top:2%;
 float: left;
 margin-left:5%;
}
.block{   /* 翻页样式*/
  margin-top:2%;
  float: right;
  margin-right:5%;
}

</style>




<style>
.class-student-table th{
     text-align: center;
     background-color: #E9EEF3;
}
.class-student-table .el-dialog__header{ /* 权限设置dialog头部样式 */
  background-color:rgb(13,113,74);
  font-family:'MicrosoftYaHei';
}
.class-student-table .el-dialog__title{ /* 权限设置dialog头部字体样式 */
  color:white;
  font-size:18px;
}
.class-student-table .el-button--primary{ /* 重设密码按钮确认 */
  width:100px;
  height:35px;
  background-color:rgb(13,113,74);
}
</style>
