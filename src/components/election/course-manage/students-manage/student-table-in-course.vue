<template>
    <div class="manage-table">
        <el-table
            :data="resultStudents"
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
                prop="stuName"
                label="姓名"
                width="90"
                align="left">                                           
            </el-table-column>                  <!--学生姓名列-->
            <el-table-column
                prop="stuNO"
                label="学号"
                width="170">
            </el-table-column>                  <!--学号列-->
            <el-table-column    
                prop="claName"
                label="班级"
                width="220">                        <!--班级列-->
            </el-table-column>
            <el-table-column
                prop="stuSex"
                label="性别"
                width="90">
            </el-table-column>                  <!--性别列-->
            <el-table-column
                prop="couName"
                label="所选课程"
                width="370"
                align="left">
            </el-table-column>                    <!--学生选的课程列-->          
            <el-table-column
                prop="evalStu"                
                label="教师评价"
                width="120"
                :filters="[ 
                    {text: '评价学生', value:0}]"
                :filter-method="filterTeacher"
                filter-placement="bottom-end">
                > 
                <template slot-scope="scope">
                    <evaluate-student :student="scope.row"></evaluate-student>
                </template>
            </el-table-column>
            <el-table-column
                prop="evalCou"
                label="学生评价"
                width="120"
                :filters="[{text:'未评价',value:0},]"
                :filter-method="filterStudent"
                filter-placement="bottom-end">
                >
                <template slot-scope="scope">
                    <span :style="{color:scope.row.evalCou<4?'#ff7a7b':''}">
                        {{scope.row.evalCou|convertValueToName}}</span>
                </template>
            </el-table-column>
            <el-table-column
                prop="state"
                label="选课状态"
                width="120"
                :filters="[{text:'未确认',value:2},]"
                :filter-method="filterState"
                filter-placement="bottom-end">
                >
                <template slot-scope="scope">
                    <span :style="{color:scope.row.state ===3?'#0d714a':'#ff7a7b'}">
                        {{scope.row.state ===3?'确认':'未确认'}}</span>
                </template>
            </el-table-column>
            <el-table-column
                prop="operate"
                label="操作"
                width="165"><!--操作列-->
                <template slot-scope="scope"> 
                    <student-details :student="scope.row"></student-details>                <!--学生详情组件-->
                    <el-button type="text" 
                    size="mini"
                    @click.native.prevent="deleteResult(scope.row.stuCouId)"
                    >                                                        <!--删除图标的样式，默认是不可编辑的，当选择器被选中时可被编辑 -->
                    <img :src="require('../../../../assets/delete-oc.png')" alt=""  >
                    </el-button>
                </template>      
            </el-table-column>
        </el-table>
        <div class="footer">
            <el-checkbox @change="toggleSelection(manageTable)" class="select-all">全选</el-checkbox>               <!---->
            <div class="amount">已选{{24}}名</div>
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
import { mapState, mapMutations, mapActions } from 'vuex';
import { delElectResults, changeStuCouState } from '../../../../api/election';
export default {
    props:['course-id'],
    components:{
      studentDetails,
      evaluateStudent
    },
    data(){
        return{                              
        }
    },
    filters:{
        /**
         * @function 把给定数值变成对应名称
         * @param {给定的数值} value
         */
        convertValueToName(value){
            //2未评(默认) 4普通 6满意 8很好 代码
            let name = '';
            switch(value){
                case 4:
                    name = '普通';
                    break;
                case 6:
                    name = '满意';
                    break;
                case 8:
                    name = '很好';
                    break;
                default:
                    name = '未评价';
            }
            return name;
        }
    },
    computed:{
        ...mapState('election',{
           resultStudents:state => state.electResultList,
           termId:state => state.currentTermId,
        })
    },
    methods:{
        ...mapActions('election',['getElectResultList']),
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
        /**@function 过滤教师对学生已评价的记录，找出未评价学生 */
        filterTeacher(value, row) {
            return row.evalStu === value
        },
        /**@function 过滤已学生已作出评价的记录，找出学生未评价的记录 */
        filterStudent(value, row) {
            return row.evalCou < 4;       //筛选教师评价的列表数据
        },
        /**@function 过滤已被确认选课的记录，找出未确认的记录 */
        filterState(value, row) {
            return row.state === value;       //筛选教师评价的列表数据
        },
        /**
         * @function 删除选定的选课结果
         * @param {学生选课Id,可以是单个或多个，多个用,连接} studentCourseIds
         */
        deleteResult(studentCourseIds){  
            let params = {stuCouIds:studentCourseIds,state:2}          
            changeStuCouState(params)
                .then(res => {
                    if(res.data.success){
                        delElectResults({stuCouIds:studentCourseIds}) 
                    }
                })
            
        }
        
    },
    mounted(){
        let url = 'stuCou!query3.action';
        let params = {selCouId:this.courseId,termId:this.termId};
        this.getElectResultList({url,params})
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










