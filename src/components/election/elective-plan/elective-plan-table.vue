<template>
    <div class="elective-plan-manage">
        <el-table
            :data="planList"
            border
            style="width: 100%"
            max-height="600"
            @selection-change="handleSelectionChange"
            ref="multipleTable"
            >                                                   
            <el-table-column
                prop="termNm"
                label="学期"
                min-width="150"
                >                                           
            </el-table-column>                 
            <el-table-column
                prop="planName"
                label="计划名称"
                min-width="150">
            </el-table-column>               
            <el-table-column    
                prop="planMeno"
                label="计划说明"
                min-width="180">                    
            </el-table-column>
            <!-- <el-table-column
                label="课程录入状态"
                min-width="50">
                <template slot-scope="scope">
                    <el-button type="text">{{scope.row.couEditState}}</el-button>
                </template>
            </el-table-column>      -->          
            <el-table-column
                label="计划状态"
                min-width="50"
                >
                <template slot-scope="scope">
                    <el-button type="text" 
                        @click="switchState(scope.row)"
                        :style='{color:scope.row.xkpState===3?"#ff7a7b":""}'>
                        {{scope.row.xkpState|convertValueToName}}
                    </el-button>
                </template>
            </el-table-column>                        
            <el-table-column
                label="操作"
                min-width="80">
                <template slot-scope="scope">
                    <el-button type="text"
                        @click="goEdit(scope.row.xkpId)">编辑</el-button>
                    <el-button icon="el-icon-delete" type="text"
                        @click="deletePlan(scope.row.xkpId)"></el-button>
                </template>                
            </el-table-column>
        </el-table>
        <!--<div class="footer">
            <el-checkbox @change="toggleSelection(resultStudents)" class="select-all">全选</el-checkbox> 
            <div class="amount">已选{{24}}名</div>
            <el-button 
                class="footer-button" 
                style="margin-left:2.2%;color:#ff7a7b"
                @click.native.prevent="deletePlan(planIds)">删除</el-button>
            <el-button 
                class="footer-button" 
                style="margin-left:1.2%;color:#14b25a"
                @click.native.prevent="confirmAll">确认选课</el-button>
            <el-pagination
                background
                layout="prev, pager, next"
                :total="140">
                :page-size="8"
            </el-pagination>
        </div>-->
    </div>
</template>


<script>
import { mapState, mapMutations, mapActions } from 'vuex';
import { delElectPlans, changePlanState } from '../../../api/election';
export default {
    props:['course-id'],
    components:{
    },
    data(){
        return{         
            planIds:'',//选课计划Id                     
        }
    },
    filters:{
        /**
         * @function 把给定数值变成对应名称
         * @param {给定的数值} value
         */
        convertValueToName(value){
            //选课计划状态 1未开启 2开启(默认) 3关闭 4完成
            let name = '';
            switch(value){
                case 1:
                    name = '未开启';
                    break;
                case 2:
                    name = '开启';
                    break;
                case 3:
                    name = '关闭';
                    break;
                case 4:
                    name = '完成';
                    break;
                default:
                    name = '未知';
            }
            return name;
        }
    },
    computed:{
        ...mapState('election',{
           planList:state => state.planList,
        })
    },
    methods:{
        ...mapActions('election',['getElectPlanList']),
        
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
         * @function 切换选课计划状态(关闭<=>开启)
         * @param {当前计划对象} curPlan
         */
        switchState:async function(curPlan){
            if(curPlan.xkpState === 2){
                await changePlanState({xkpId:curPlan.xkpId,xkpState:3});
                this.getElectPlanList();
            }else if(curPlan.xkpState === 3){
                await changePlanState({xkpId:curPlan.xkpId,xkpState:2})
                this.getElectPlanList();
            }
        },
        /**
         * @function 跳转到编辑选课计划页面
         * @param {选课计划Id} planId
         */
        goEdit(planId){
            this.$router.push({name:'AddElectivePlan',query:{planId}})
        },
        /**
         * @function 删除选定的选课结果
         * @param {学生选课Id} planId
         */
        deletePlan:async function(planId){  
            let params = {
                    xkpId:planId,
                    xkpState:4//1未开启 2开启(默认) 3关闭 4完成
                }          
            changePlanState(params)
                .then(res => {
                    if(res.data.success){
                        delElectPlans({xkpIds:planId})
                            .then(res => {
                                if(res.data.success){
                                    this.getElectPlanList()
                                    this.$message.success('删除成功！');
                                }else{
                                    this.$message.error(res.data.message);
                                }
                            }) 
                    }
                })
            
        },                     
    },
    mounted(){
        

    }
}
</script>
<style scoped>
    .elective-plan-manage{
        padding:10px;
    }
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

<style>
    .elective-plan-manage th{       /*  表头字体居中 */
        text-align: center;
        background-color:#f6f9fb;
        color: #2b2b32;
	}
</style>











