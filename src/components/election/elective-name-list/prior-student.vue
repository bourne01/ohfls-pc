<template>
    <div class="prior-election"
        v-loading="isLoading"
        element-loading-text="拼命设置中"
        background="rgba(0, 0, 0, 0.8)">
        <el-button 
            class="prior-button"
            icon="el-icon-circle-check"
            @click="isShow=true">优秀学生优先选择</el-button>      
        <el-dialog
            title="优秀学生优先选择"
            :visible.sync="isShow"
            width="400px"
            top="15vh"
            ref="multipleTable"
            :modal="true"
            >
            <ul>
                <li>
                    <label for="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学期</label>
                    <el-select v-model="priorElection.term">
                        <el-option
                            v-for="item in terms"
                            :key="item.id"
                            :label="item.name"
                            :value="item.id">
                        </el-option>
                    </el-select>
                </li>
                <li>
                    <label for="">
                        开启时间
                    </label>
                    <el-date-picker
                        v-model="priorElection.startTime"
                        type="datetime"
                        placeholder="选课开启时间"
                        value-format="yyyy-MM-dd HH:mm:ss">
                    </el-date-picker>
                </li>
                <li>
                    <label for="">
                        关闭时间
                    </label>
                    <el-date-picker
                        v-model="priorElection.endTime"
                        type="datetime"
                        placeholder="选课关闭时间"
                        value-format="yyyy-MM-dd HH:mm:ss">
                    </el-date-picker>
                </li>
                <li>
                    <label for="">
                        完成时间
                    </label>
                    <el-date-picker
                        v-model="priorElection.overTime"
                        type="datetime"
                        placeholder="选课完成时间"
                        value-format="yyyy-MM-dd HH:mm:ss">
                    </el-date-picker>
                </li>
                <li>
                    <el-button class="cancel" @click="isShow=false">取消</el-button>
                    <el-button class="confirm" @click="setPriorStudentElectTime">确定</el-button>
                </li>
            </ul>
        </el-dialog> 
    </div>
       
</template>

<script>
import { getSelector } from '../../../api/public.js'
import { getElectResults, editElectDuty } from '../../../api/election.js';
import { xhrErrHandler } from '../../../utils/util.js';
import { mapState } from 'vuex';
export default {
    data(){
        return{
            isShow:false,
            priorElection:{},
            terms:[],//学期列表
            isLoading:false,
        }
    },
    computed:{
        ...mapState('election',{
            currentPlanId:state => state.currentPlanId//当前所选的计划Id
        }),
    },
    methods:{
        /**
         * @function 获取优选学生名单,从中取得所有有选课任务的学生Id
         * 然后再改变选课时间
         */
        setPriorStudentElectTime(){
            this.isLoading = true;
            this.isShow = false;
            let url = "stuCou!query3.action";
            let params = {
                    'termId':this.priorElection.term,
                    'evalStu':10,}
            getElectResults(url,params)
                .then(res => {
                    if(res.data.success){
                        let priorStudentList = res.data.dataList;
                        let priorStudentIds = [];//优选学生Id列表
                        for(let item of priorStudentList)
                            priorStudentIds.push(item.stuId);
                        editElectDuty({
                            xkpId:this.currentPlanId,
                            stuIds:priorStudentIds.toString(),
                            startTime:this.priorElection.startTime,
                            endTime:this.priorElection.endTime,
                            overTime:this.priorElection.overTime,
                            svType:2//如果已存在，则进行更新
                        })
                        .then(res => {
                            if(res.data.success){
                                this.$root.bus.$emit('update-task-list');//通知组件'student-table'，更新列表
                                this.$message.success(res.data.message);                                
                            }else{
                                this.$message.error(res.data.message)
                            }
                            this.isLoading = false;
                        })
                        .catch(err => {
                            xhrErrHandler(err,this.$router,this.$message);
                            this.isLoading = false;
                        })
                    }
                })
                .catch(err => {
                    xhrErrHandler(err,this.$router,this.$message)
                })
        }
        
    },
    mounted(){
        /**
         * @function 获取学期列表
         */
        getSelector({f:'uxTerm',simple:0})
            .then(res => {
                this.terms = res.data.dataList;                
            })       
                        
    }
}
</script>

<style>
    .prior-button i.el-icon-circle-check{
        color:#5b9bfd;
        font-size:16px;
    }
    .prior-election .el-dialog  {    /*设置弹框整体的样式*/
        height:430px;   
    }                                                               
    .prior-election .el-dialog__header{             /*设置弹框的头部*/
        background-color :#0d714a;
        height: 20px;
        color:#707079; 
        margin:0px;
    }
    .prior-election  .el-dialog__header .el-dialog__title{       /*设置头部的标题样式*/ 
        color:white;
        float: left;
        line-height:10px;
        margin-left:20px;
        font-size:16px;
        font-family:'MicrosoftYaHei';
    }
    .prior-election .el-dialog__headerbtn .el-dialog__close{      /*设置头部关闭的样式*/
        color:white;
        font-size:24px;
        margin-top:-30px;
        }
    
    .prior-election .el-dialog__body {                        /*设置弹框body内的内填充为0*/
        padding:0;  
        width:100%;
    }
</style>
<style scoped>
    .prior-button{        /*优秀学生优先选择的按钮*/
        float: left;    
        height: 32px;
        border: 1px solid #e5e9ec;
        line-height: 32px;
        font-size: 14px;
        padding:0 20px;
        border-radius:2px;
        margin-left:40px;        
    } 
    .prior-election li{
        height:4.5em;
    }
    label{
        margin-right:5px;
    }
    .prior-election li:first-child{
        margin-top:40px;
    }
    .prior-election li:last-child{
        margin-top:20px;
    }
    .confirm{
        background-color:#5b9bfd;
        color:#fff;
        font-size:16px;
    }
    .cancel{
        margin-right:10px;
        font-size:16px;
    }
</style>