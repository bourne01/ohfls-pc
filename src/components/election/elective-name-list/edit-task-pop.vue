<template>
    <div class="edit-task">
        <el-button type="text" size="small" @click="isShow=true">编辑</el-button>
        <el-dialog
            title="编辑选课任务"
            :visible.sync="isShow"
            width="700px"
            top="15vh"
            ref="multipleTable"
            class="edit-task-dialog"
            :modal="true">
            <el-row class="first-row">
                <el-col :span="3">
                    <label for="">姓名</label>
                </el-col>
                <el-col :span="9">
                    <el-input disabled v-model="electiveTask.stuName"></el-input>
                </el-col>
                <el-col :span="3">
                    <label for="">学号</label>
                </el-col>
                <el-col :span="9">
                    <el-input disabled v-model="electiveTask.stuNO"></el-input>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="3">
                    <label for="">开启时间</label>
                </el-col>
                <el-col :span="9">
                    <el-date-picker
                        v-model="electiveTask.startTime"
                        type="datetime"
                        placeholder="选课开启时间"
                        value-format="yyyy-MM-dd HH:mm:ss">
                    </el-date-picker>
                </el-col>
                <el-col :span="3">
                    <label for="">关闭时间</label>
                </el-col>
                <el-col :span="9">
                    <el-date-picker
                        v-model="electiveTask.endTime"
                        type="datetime"
                        placeholder="选课关闭时间"
                        value-format="yyyy-MM-dd HH:mm:ss">
                    </el-date-picker>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="3">
                    <label for="">完成时间</label>
                </el-col>
                <el-col :span="9">
                    <el-date-picker
                        v-model="electiveTask.overTime"
                        type="datetime"
                        placeholder="选课完成时间"
                        value-format="yyyy-MM-dd HH:mm:ss">
                    </el-date-picker>
                </el-col>
                <el-col :span="3">
                    <label for="">选课计划</label>
                </el-col>
                <el-col :span="9">
                    <el-select v-model="electiveTask.planNO" disabled="">
                        <el-option
                            v-for="item in electPlans"
                            :key="item.planNO"
                            :label="item.planName"
                            :value="item.planNO">
                        </el-option>
                    </el-select>
                </el-col>
            </el-row>
            <el-row class="task-state">
                <el-col :span="3">
                    <label for="">任务状态</label>
                </el-col>
                <el-col :span="21">
                    <el-radio-group v-model="taskState">
                        <el-radio :label="1">未开启</el-radio>
                        <el-radio :label="2">开启</el-radio>
                        <el-radio :label="3">关闭</el-radio>
                        <el-radio :label="4">完成</el-radio>
                    </el-radio-group>
                    
                </el-col>
            </el-row>
            <el-row>
                <el-col>
                    <el-button class="confirm" @click="editElectTask">确定</el-button>
                </el-col>
            </el-row>
        </el-dialog>
    </div>
</template>

<script>
import { mapState, mapMutations } from 'vuex';
import { editElectDuty } from '../../../api/election';
import { xhrErrHandler } from '../../../utils/util';
export default {
    props:['elective-task'],
    data(){
        return{
            /* electiveTask:{},//学生选课任务对象 */
            isShow:false,
            taskState:this.electiveTask.taskState
        }
    },
    computed:{
        ...mapState('election',{
            electPlans:state => state.planList,
            currentPlanId:state => state.currentPlanId,
        }),
        
    },
    methods:{
        ...mapMutations('election',['GET_TASKLIST']),
        /**
         * @function 编辑选课任务
         */
        editElectTask(){
            this.isShow = false;//关闭弹窗
            editElectDuty({
                xkpId:this.electiveTask.xkpId,
                stuIds:this.electiveTask.stuId,
                startTime:this.electiveTask.startTime,
                endTime:this.electiveTask.endTime,
                overTime:this.electiveTask.overTime,
                taskState:this.taskState
            })
            .then(res => {
                if(res.data.success){
                    this.$message.success(res.data.message);
                    this.$root.bus.$emit('update-task-list');
                }else{
                    this.$message.error(res.data.message)
                }
            })
            .catch(err => {
                xhrErrHandler(err,this.$router,this.$message)
            })
        }
    },
}
</script>


<style scoped>
    .edit-task{
        display:inline-block;
    }
    .el-row{
        margin-bottom:25px;
    }
    .first-row>.el-col-3{
        text-indent: 2em;
    }
    .el-col-3{
        height:40px;
        line-height:40px;
    }
    .el-select{
        width:100%;
    }
    .el-row:last-child{
        margin-top:40px;
    }
    .task-state>.el-col-21{
        text-align: left;
        height:40px;
        line-height:40px;
    }
    .confirm{
        background-color:#5b9bfd;
        color:#fff;
        font-size:16px;
    }
</style>

<style>
    .edit-task .el-date-editor.el-input,
    .el-date-editor.el-input__inner{
        width:100%;
    }
    .edit-task .el-dialog  {    /*设置弹框整体的样式*/
        height:430px;   
    }                                                               
    .edit-task .el-dialog__header{             /*设置弹框的头部*/
        background-color :#0d714a;
        height: 50px;
        color:#707079; 
        margin:0px;
    }
    .edit-task  .el-dialog__header .el-dialog__title{       /*设置头部的标题样式*/ 
        color:white;
        float: left;
        line-height:10px;
        margin-left:20px;
        font-size:16px;
        font-family:'MicrosoftYaHei';
    }
    .edit-task .el-dialog__headerbtn .el-dialog__close{      /*设置头部关闭的样式*/
        color:white;
        font-size:24px;
        margin-top:-30px;
        }
    
    .edit-task .el-dialog__body {                        /*设置弹框body内的内填充为0*/
        padding:40px 20px;  
        width:100%;
    }
</style>