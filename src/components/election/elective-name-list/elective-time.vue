<template>
    <div class="set-time" 
        v-loading="isLoading"
        element-loading-text="拼命设置中"
        background="rgba(0, 0, 0, 0.8)">
        <el-button 
            class="set-time-btn"
            icon="el-icon-time"
            @click="isShow=true">设置学生选课时间
        </el-button>
        <el-dialog
            title="设置学生选课时间"
            :visible.sync="isShow"
            width="400px"
            top="15vh"
            ref="multipleTable"
            :modal="true"
            >
            <ul>
                <li>
                    <label for="">
                        开启时间
                    </label>
                    <el-date-picker
                        v-model="eletiveTask.startTime"
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
                        v-model="eletiveTask.endTime"
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
                        v-model="eletiveTask.overTime"
                        type="datetime"
                        placeholder="选课完成时间"
                        value-format="yyyy-MM-dd HH:mm:ss">
                    </el-date-picker>
                </li>
                <li>
                    <el-button class="cancel" @click="isShow=false">取消</el-button>
                    <el-button class="confirm" @click="setElectTime">确定</el-button>
                </li>
            </ul>
        </el-dialog> 
    </div>
       
</template>

<script>
import { mapState, mapActions } from 'vuex';
import { xhrErrHandler } from '../../../utils/util.js'
import { editElectDuty } from '../../../api/election.js';
export default {
    data(){
        return{
            isShow:false,
            isLoading:false,
            eletiveTask:{},
        }
    },
    computed:{
        ...mapState('election',{
            currentPlanId:state => state.currentPlanId
        })
    },
    methods:{
        ...mapActions('election',['getTaskList']),
        /**@function 获取所有选课任务,从中取得所有有选课任务的学生Id
         * 然后再改变选课时间
         */
        setElectTime(){
            this.isLoading = true;
            this.isShow = false;
            let url ='xkTask!query2.action';
			let params = {
				xkpId:this.currentPlanId,
				limit:100000,
				start:0
			};
			this.getTaskList({url,params})
				.then(res => {
                    let taskList = res.dataList;
                    let stuIds = [];
                    for(let item of taskList)
                        stuIds.push(item.stuId);
                    editElectDuty({
                            xkpId:this.currentPlanId,
                            stuIds:stuIds.toString(),
                            startTime:this.eletiveTask.startTime,
                            endTime:this.eletiveTask.endTime,
                            overTime:this.eletiveTask.overTime,
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
				})
				.catch(err => {
					xhrErrHandler(err,this.$router,this.$message)
				})
        }
    }
}
</script>

<style>
    .set-time-btn i.el-icon-time{
        color:#5b9bfd;
        font-size:16px;
    }
    .set-time .el-dialog  {    /*设置弹框整体的样式*/
        height:360px;
    
    
    }                                                               
    .set-time .el-dialog__header{             /*设置弹框的头部*/
        background-color :#0d714a;
        height: 20px;
        color:#707079; 
        margin:0px;
    }
    .set-time  .el-dialog__header .el-dialog__title{       /*设置头部的标题样式*/ 
        color:white;
        float: left;
        line-height:10px;
        margin-left:20px;
        font-size:16px;
        font-family:'MicrosoftYaHei';
    }
    .set-time .el-dialog__headerbtn .el-dialog__close{      /*设置头部关闭的样式*/
        color:white;
        font-size:24px;
        margin-top:-30px;
    }
    
    .set-time .el-dialog__body {                        /*设置弹框body内的内填充为0*/
        padding:0;  
        width:100%;
    }
</style>
<style scoped>
  .set-time-btn{           /*设置学生选课时间的按钮*/
        float: left;    
        height: 32px;
        border: 1px solid #e5e9ec;
        line-height: 32px;
        font-size: 14px;
        padding:0 20px;
        border-radius:2px;
        margin-left:40px;
    }    
    li{
        height:4.5em;
    }
    label{
        margin-right:5px;
    }
    li:first-child{
        margin-top:40px;
    }
    li:last-child{
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


