<template>
    <article class="plan-wrapper"> 
        <section>
            <header><span>课程消息</span><img src="" alt=""></header>                  <!--新增课程页面-->
            <div class="flex">
                <div class="plan-name">
                    <label for=""><span>*</span>计划名称</label>
                    <el-input placeholder="输入名称" v-model="plan.name"></el-input>
                </div>
                <div class="plan-id">
                    <label for=""><span>*</span>计划编号</label>
                    <el-input placeholder="输入编号" v-model="plan.NO"></el-input>
                </div>
            </div>
            <div>
                <div class="teaching-site">
                    <label for=""><span>*</span>学期</label>
                    <el-select  v-model="plan.termId">
                        <el-option
                            v-for="item in termList"
                            :key="item.id"
                            :label="item.name"
                            :value="item.id">
                        </el-option>
                    </el-select>
                </div>                        
            </div>  
            <div class="plan-intro">
                <label for="">计划说明</label>
                <el-input type="textarea" v-model="plan.memo"></el-input>
            </div>
            <div>
                <label for="">报名时间<span class="end-time"></span></label>                
                <el-date-picker
                    v-model="plan.time"
                    type="datetimerange"
                    align="right"
                    start-placeholder="开始日期"
                    end-placeholder="结束日期"
                    @change="onDateTimeChange"
                    value-format="yyyy-MM-dd HH-mm-ss"
                    :default-time="['12:00:00', '08:00:00']">
                </el-date-picker>
            </div>   
            <div class="flex ratio">
                <div>
                    <label for="">特优比率(%)</label>
                    <el-input v-model="plan.ultimateRatio"></el-input>
                </div>
                <div>
                    <label for="">优秀比率(%)</label>
                    <el-input v-model="plan.excellentRatio"></el-input>
                </div>
                <div>
                    <label for="">良好比率(%)</label>
                    <el-input v-model="plan.goodRatio"></el-input>
                </div>
            </div>  
            <div class="flex credit">
                <div>
                    <label for="">特优学分</label>
                    <el-input  v-model="plan.ultimateCredit"></el-input>
                </div>
                <div>
                    <label for="">优秀学分</label>
                    <el-input v-model="plan.excellentCredit"></el-input>
                </div>
                <div>
                    <label for="">良好学分</label>
                    <el-input v-model="plan.goodCredit"></el-input>
                </div>
            </div>
            <div>
                <span class="open">开放新增/编辑</span>
                <el-switch
                    v-model="plan.isOpen"
                    active-color="#0d714a"
                    inactive-color="#ff4949">
                </el-switch>
            </div>
            <div class="save">
                <el-button @click="submit">{{actionName}}</el-button>
            </div>      
        </section>          
    </article>
</template>

<script>
import { addElectPlan, editElectPlan } from '../../../api/election.js' 
import { getSelector } from '../../../api/public.js'
export default {
    data() {
        return {
            plan:{},//计划对象
            termList:[],//学期列表
            actionName:'保存并返回',//在新增选课计划下显示“保存并返回”，在编辑选课计划下显示“编辑并返回”
        };
    },
    methods: {
        /**@function 提交选课计划*/
        submit(){
            //计划名称、编号与学期，三者缺一则无法提交
            if(!this.plan.id || !this.plan.name || !this.plan.termId){
                this.$message("计划名称、计划编号与学期三者不能为空");
               return;
            }
            let planStart = undefined;//计划开启时间
            let planEnd = undefined;//计划结束时间
            if(typeof this.plan.time === 'object'){
                planStart = this.plan.time[0];
                planEnd = this.plan.time[1];
            }
            
            if((this.plan.ultimateRatio/100 + this.plan.excellentRatio/100 + this.plan.goodRatio/100) > 1){                
                this.$message('特优、优秀和良好三者比率之和超过100%');
                return;
            }                

            let params = {
                termId:this.plan.termId,
                planNO:this.plan.NO,
                planName:this.plan.name,
                planMeno:this.plan.memo,
                planStart,
                planEnd,
                credit1:this.plan.ultimateCredit,
                credit2:this.plan.excellentCredit,
                credit3:this.plan.goodCredit,
                lvRate1:this.plan.ultimateRatio/100,
                lvRate2:this.plan.excellentRatio/100,
                lvRate3:this.plan.goodRatio/100,
                couEditState:this.plan.isOpen?2:4,
                xkpId:this.plan.xkpId
            }
            let action;
            if(this.$route.query.planId)//如果存在选课计划Id则是编辑模式
                action = (param) => {return editElectPlan(param)}
            else//如果选课计划Id为空，则是新增选课计划模式
                action = (param) => {return addElectPlan(param)}
            action(params)
                .then(res => {
                    console.log(res)
                    if(res.data.success){
                        this.$message(res.data.message);
                        this.$router.push('/home/election')
                    }else{
                        switch(res.data.type){//错误类型
                            case 1:
                                break;
                            case 2:
                                this.$message('发生异常，请联系管理员');
                                break;
                            default:{
                                this.$message('发生未知错误，请联系管理员');
                            }
                        }
                    }
                })
                .catch(err => {
                    console.log(err);
                })
            //this.$router.push('/home/election')
        },
        /**@function 监听日期选择器事件，一旦发生日期改变，则处理事件 */
        onDateTimeChange(){
            console.log(this.plan.time);
        },       
    },
    mounted(){
        let planId = this.$route.query.planId
        if(planId){//如果编号非空，则是来自点击选课计划而来，不是来自点击新增计划按钮而来
            this.actionName = "编辑并返回"
            let planList = this.$store.state.election.planList;
            for(let plan of planList){
                if(plan.xkpId === planId){
                    let time = [];
                    if(plan.planStart){
                        time[0] = new Date(plan.planStart);
                    }
                    if(plan.planEnd){
                        time[1] = new Date(plan.planEnd);
                    }
                    this.plan = {
                        termId:plan.termId,
                        id:plan.planNO,
                        name:plan.planName,
                        memo:plan.planMeno,
                        time,
                        xkpId:planId,
                        ultimateCredit:plan.credit1,
                        excellentCredit:plan.credit2,
                        goodCredit:plan.credit3,
                        ultimateRatio:plan.lvRate1*100,
                        excellentRatio:plan.lvRate2*100,
                        goodRatio:plan.lvRate3*100,
                        isOpen:plan.couEditState==2?true:false
                    }
                }
            }
        };
        let params = {
            f:'uxTerm',
            /* state:2, */
            simple:0
        }
        getSelector(params)
            .then(res => {
                console.log(res)
                this.termList = res.data.dataList;
            })
            .catch(err => {

            })
    }
}
</script>

<style>    
    .plan-wrapper .el-input__inner{
        background-color:#f6f9fb;
    }
    .plan-wrapper .el-textarea__inner{
        background-color:#f6f9fb;
        min-height:88px!important;
    }
</style>


<style scoped>    
    .plan-wrapper section{
        padding-top:50px;
        background-color:#fff;
        text-align:left;
        padding-bottom:10px;
    }
    section.plan-setting{
        margin-top:10px;
    }
    .plan-wrapper section>header{
        padding-left:70px;
        margin-bottom:40px;
        font-size: 24px;
        font-family: 'MicrosoftYaHei';
        color:#1a1a1f;
    }
    .plan-wrapper section>div{
        padding-left:70px;
        margin-bottom:40px;
    }   
    
    .flex{
        display:flex;
    }
    label{
        display:block;
        margin-bottom:10px;
        font-size:14px;
    }
    label>span{
        color:red;
        margin-right:5px;
    }
   .plan-name .el-input,
   .teaching-site .el-select{
       width:392px;
       margin-right:38px;
   }
   .plan-id .el-input,
   .plan-type .el-select{
       width:320px;
   }
   .plan-intro .el-textarea{
       width:752px;
       /* min-height:88px!important; */
   }  
    .limit,.ratio,.credit{
        justify-content:space-between;   
        width:752px;     
    }
    .limit .el-input{
        width:150px;
    }
    .ration .el-input,
    .credit .el-input{
        width:200px;
    }
    .lowest-credit .el-input{
        width:200px;
    } 
    .open{
        font-size:14px;
        color:#2b2b32;
        margin-right:150px;   
    }
    .save .el-button{               /*保存并返回按钮的样式*/
        width: 172px;
        height: 44px;
        font-size: 16px;
        line-height:44px;
        background-color: #0d714a;
        border: none;
        color:#fff;
        padding:0;
    }
</style>

















