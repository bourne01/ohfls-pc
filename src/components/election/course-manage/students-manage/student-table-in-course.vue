<template>
    <div class="manage-table">
                    <el-table
                    :data="manageTable"
                    border
                    style="width: 100%"
                    max-height="520"
                    @selection-change="handleSelectionChange"
                    ref="multipleTable"
                >                                                       <!--管理名单的表格-->
                    <el-table-column
                    type="selection"
                    width="70">
                    </el-table-column>                      <!--复选框-->
                    <el-table-column
                        prop="studentName"
                        label="姓名"
                        width="90"
                        align="left">                                           
                    </el-table-column>                  <!--学生姓名列-->
                    <el-table-column
                        prop="studentID"
                        label="学号"
                        width="170">
                    </el-table-column>                  <!--学号列-->
                    <el-table-column    
                        prop="class"
                        label="班级"
                        width="220">                        <!--班级列-->
                    </el-table-column>
                    <el-table-column
                        prop="gender"
                        label="性别"
                        width="90">
                    </el-table-column>                  <!--性别列-->
                    <el-table-column
                        prop="selectedCourse"
                        label="所选课程"
                        width="370"
                        align="left">
                    </el-table-column>                    <!--学生选的课程列-->          
                    <el-table-column
                        prop="teacherEvaluate"
                        label="教师评价"
                        width="120"
                        :filters="[ { text: '评价学生', value:2},
                        {text:'已评价',value: 1},]"
                        :filter-method="filterTeacher"
                        filter-placement="bottom-end">
                        >                                           <!--教师评价列-->
                        <template slot-scope="scope">
                            <evaluate-student :evaluate="scope.row.teacherEvaluate"></evaluate-student>         <!--评价学生的列-->
                        </template>
                    </el-table-column>
                    <el-table-column
                        prop="studentEvaluate"
                        label="学生评价"
                        width="120"
                        :filters="[ { text: '已评价', value:'已评价'},
                        {text:'未评价',value:'未评价'},]"
                        :filter-method="filterStudent"
                        filter-placement="bottom-end">
                        >                                       <!--学生评价列-->
                    </el-table-column>
                    <el-table-column
                        prop="courseStatus"
                        label="选课状态"
                        width="120"
                        :filters="[ { text: '已确认', value:'已确认'},
                        {text:'未确认',value:'未确认'},]"
                        :filter-method="filterStatus"
                        filter-placement="bottom-end">
                        >
                    </el-table-column>
                    <el-table-column
                        prop="operate"
                        label="操作"
                        width="165"
                    >                               <!--操作列-->
                        <template slot-scope="scope"> 
                            <student-details></student-details>                <!--学生详情组件-->
                                    <el-button type="text" 
                                    size="mini"
                                    @click.native.prevent="deleteRow(scope.$index,manageTable)"
                                    >                                                        <!--删除图标的样式，默认是不可编辑的，当选择器被选中时可被编辑 -->
                                    <img :src="require('../../../../assets/delete-oc.png')" alt=""  >
                                    </el-button>
                                    </template>      
                                </el-table-column>
                    </el-table>
        <div class="footer">
            <el-checkbox @change="toggleSelection(manageTable)" class="select-all">全选</el-checkbox>               <!---->
            <div class="amount">已选{{scope}}名</div>
            <el-button class="footer-button" style="margin-left:2.2%;color:#ff7a7b">删除</el-button>
            <el-button class="footer-button" style="margin-left:1.2%;color:#707079">批量审核</el-button>
            <el-button class="footer-button" style="margin-left:1.2%;color:#14b25a">确认选课</el-button>
            <el-pagination
            background
            layout="prev, pager, next"
            :total="140">
            :page-size="8"
            </el-pagination>
   </div>
    </div>
</template>


<script>
import studentDetails from './student-details.vue'
import evaluateStudent from './evaluate-student.vue'
export default {
    components:{
      studentDetails,
      evaluateStudent
    },
    data(){
        return{
                manageTable:[{
                class:'2016级/小学一（5）班',
                studentName:'陈力豪',
                studentID:'120200405',
                gender:'男',
                selectedCourse:'快乐阅读',
                teacherEvaluate:2,
                studentEvaluate:'未评价',
                courseStatus:'已确认',
                },
                {
                class:'2016级/小学一（5）班',
                studentName:'陈力豪',
                studentID:'120200405',
                gender:'男',
                selectedCourse:'快乐阅读',
                teacherEvaluate:1,
                studentEvaluate:'已评价',
                courseStatus:'未确认',
                },
                {
                class:'2016级/小学一（5）班',
                studentName:'陈力豪',
                studentID:'120200405',
                gender:'男',
                selectedCourse:'快乐阅读',
                teacherEvaluate:1,
                studentEvaluate:'已评价',
                courseStatus:'未确认',
                },
                {
                class:'2016级/小学一（5）班',
                studentName:'陈力豪',
                studentID:'120200405',
                gender:'男',
                selectedCourse:'1',
                teacherEvaluate:2,
                studentEvaluate:'未评价',
                courseStatus:'未确认',
                },
                {
                class:'2016级/小学一（5）班',
                studentName:'陈力豪',
                studentID:'120200405',
                gender:'男',
                selectedCourse:'快乐阅读',
                teacherEvaluate:2,
                studentEvaluate:'已评价',
                courseStatus:'已确认',
                },
                {
                class:'2016级/小学一（5）班',
                studentName:'陈力豪',
                studentID:'120200405',
                gender:'男',
                selectedCourse:'快乐阅读',
                teacherEvaluate:1,
                studentEvaluate:'已评价',
                courseStatus:'未确认',
                }],
              
        }
        
    },
    methods:{
         toggleSelection(rows) {   /* 全选复选框*/
        if (rows) {
          rows.forEach(row => {
            this.$refs.multipleTable.toggleRowSelection(row);
          });
        } else {
          this.$refs.multipleTable.clearSelection();
        }
        },
       handleSelectionChange(val) {
        this.multipleSelection = val;
        },
        filterTeacher(value, row) {
        return row.teacherEvaluate === value;       //筛选教师评价的列表数据
        },
          filterStudent(value, row) {
        return row.studentEvaluate === value;       //筛选教师评价的列表数据
        },
          filterStatus(value, row) {
        return row.courseStatus === value;       //筛选教师评价的列表数据
        },
         deleteRow(index, rows) {            //删除按钮的弹框，并进行进一步的确认或者取消
        this.$confirm('确认删除该课程吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => { 
          rows.splice(index, 1);
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
    }
}
}
</script>
<style scoped>
    .footer{                                /*表格页脚样式*/
        height: 60px;
        width: 100%;
        background-color:rgb(228, 230, 231);
        padding-top: 15px;
    }
    .select-all{                /*全选样式*/
    float: left;
    margin-left: 1.5%
  }
     .footer-button{            /*页脚按钮全局样式*/
    float: left;
    width: 88px;
    height: 28px;
    line-height: 32%;
    font-size: 12px;
    }
    .amount{            /*统计选择的样式*/
    float: left;
    font-family: 'Simsun';
    font-size: 12px;
    color: #707079;
    margin-left: 10px;
    margin-top: 0px;
  }
  .el-pagination{           /*翻页的样式*/
    float: right;
  }
</style>










