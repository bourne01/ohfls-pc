<template>
   <div class="teacher-table">
    <div class="list"> <!--这是列表定义-->
             <el-table
                ref="multipleTable"
                :data="teacherTable"
                tooltip-effect="dark"
                border
                width=100%
                @selection-change="handleSelectionChange"
                >
             <el-table-column                
                type="selection"
                width="70">
             </el-table-column>   
             <el-table-column
                prop="thrNO"
                label="教师编号"
                width="190">
             </el-table-column>
             <el-table-column
                prop="thrName"
                label="姓名"
                width="200">
             </el-table-column>
             <el-table-column
                prop="thrSex"
                label="性别"
                width="100">
             </el-table-column>
             <el-table-column
                prop="explain"
                label="简介"
                width="450">
             </el-table-column>
             <el-table-column
                prop="thrTo"
                label="去向"
                width="100"
                :filters="[{ text: '任教', value: '任教' }, { text: '已辞职', value: '已辞职' }, { text: '休假', value: '休假' }]"
                :filter-method="filterWhere"
                filter-placement="bottom-end">
             </el-table-column>
             <el-table-column
                prop="thrState"
                label="状态"
                width="100"
                :filters="[{ text: '正常', value: '正常' }, { text: '历史', value: '历史' }]"
                :filter-method="filterstate"
                filter-placement="bottom-end">
                <template slot-scope="scope">
                    <status :current-row ="scope.row"></status>
                </template>
              </el-table-column>
           <el-table-column
                prop="remark"
                label="备注"
                width="180">
           </el-table-column>
             <el-table-column
                prop="introduce"
                label="操作"
              
                >
             <template slot-scope="scope"><!--这是功能按钮-->
              <el-button type="text" >编辑</el-button>
              <el-button type="text" size="small" @click="dialogVisible2 = true">重设密码</el-button>
              <el-button type="text" size="small" @click="dialogVisible = true">权限设置</el-button>
           </template>
          </el-table-column>
         </el-table>    <!--这是翻页功能-->
               <el-pagination class="pagination"
               background
               layout="prev, pager, next"
               :total="1000">
               </el-pagination>
          <div class="leftButton"> 
              <el-button @click="toggleSelection(tableData)" >全选</el-button>
              <el-button @click.native.prevent="deleteRow(scope.$index, tableData)" type="text"  size="small" class="buttonStyle">批量删除</el-button>
        </div>
    </div>
</div>
</template>
<script>
import Status from './status'
import {mapActions,mapState} from 'vuex'
  export default {
      components:{
          Status,
      },
    data() {
      return { 
        functionList:[{}],
        dialogVisible: false,
        dialogVisible2: false,
        form: { /* 表格 */
          name:'',
          region: '',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          resource: '',
          desc: '',
          number:'',
        },
         labelPosition: 'right',
        formLabelAlign: {
          name: '',
          region: '',
          type: ''
        },
         multipleSelection: [], /*全选事件 */
        formLabelWidth: '120px'
      }
    },
    methods: {
      toggleSelection(rows) {   /* 全选按钮*/
        if (rows) {
          rows.forEach(row => {
            this.$refs.multipleTable.toggleRowSelection(row);
          });
        } else {
          this.$refs.multipleTable.clearSelection();
        }
      },
      deleteRow(index, rows) { /* 删除按钮 */
        rows.splice(index, 1);
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
        
      },
       filterWhere(value, row) {
        return row.state === value;
      },
       filterstate(value, row) {
        return row.state === value;
      },
      ...mapActions('teacher',['getTeacherTable'])
      },
      computed:{
        ...mapState('teacher',{
          teacherTable:state => state.teacherTable
        })
      },
      mounted(){
        let url = 'thr!query.action';
        let params = {start:0};
        this.getTeacherTable({url,params:{}})
      }
    
    }
  


</script>
<style  scoped>
.buttonStyle{ /* 批量删除按钮 */
  width: 100px;
  height: 36px;
  background: white;
  color: red;
  border: 1px solid rgb(235, 238, 245);
}


</style>


<style>
.list th{       /*  表头字体居中 */
  text-align: center;
  background-color: #E9EEF3;
}
.teacher-table .el-dialog__header{ /* 权限设置dialog头部样式 */
  background-color:rgb(13,113,74);
  font-family:'MicrosoftYaHei';
  height: 55px;
}
.teacher-table .el-dialog__title{ /* 权限设置dialog头部字体样式 */
  color:white;
  font-size:18px;
  float: left;
}
.teacher-table .el-button--primary{ /* 权限设置按钮确认 */
  width:100px;
  height:35px;
  background-color:rgb(13,113,74);
}
.teacher-table .el-pagination{ /* 翻页按钮样式 */
  float:right;
}
.teacher-table .el-pagination{ /* 翻页按钮样式 */
  margin-top: 15px;
}
.teacher-table .leftButton{ /* 全选、删除按钮样式*/
  margin-top: 10px;
  margin-left:20px;
  height: 25px;
  float: left;
}
.teacher-table .head-style{ /* 教师功能权限头像 */
  width: 50px;
  height: 50px;
}
.teacher-table .nameStyle{
margin:0 0 5px 20px;
}
.teacher-table .el-dialog__headerbtn .el-dialog__close{
  color:white;
}
.teacher-table .el-table--scrollable-y .el-table__body-wrapper{
  overflow: hidden;
}
</style>
