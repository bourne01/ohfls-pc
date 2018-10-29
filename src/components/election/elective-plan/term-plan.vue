<template>
    <div class="term-plans">
        <div class="term-plan" 
            :class="{active:idx===actIdx}"
            @click="onClick(idx,plan.xkpId,plan.termId)"
            @mouseover="hoverIdx=idx"
            @mouseout="hoverIdx=-1"
            v-for="(plan,idx) in plans" :key="idx" v-if="idx<planNums">
            <div>
                <span class="term-txt">{{plan.termNm|getYear}}</span>
                <span 
                    class="term-border"
                    :class="{activeTerm:idx===actIdx}"
                >学年</span>
            </div>
            <div class="term">第{{plan.termNm|getTermValue}}学期</div>
            <img 
                class="plan-done" 
                :src="require('../../../assets/completed.png')" alt=""
                v-if="idx!==0">
            <el-button 
                icon="el-icon-close" size="mini" circle
                v-if="idx===hoverIdx"
                @click.stop="removePlan(plan)"></el-button>
        </div>
    </div>
    
</template>

<script>
import { getTerms } from '../../../api/public.js'
import { getElectPlans, changePlanState, delElectPlans } from '../../../api/election.js'
import { xhrErrHandler } from '../../../utils/util.js'
import { mapMutations } from 'vuex';
export default {
    props:['idx'],
    data(){
        return{
            actIdx:0,//默认最新学期激活
           // plans:[],//选课计划列表
            planNums:0,//屏幕展示学科计划数量，由分辨率而定
            hoverIdx:-1,
        }
    },
    computed:{
        plans:function(){
            return this.$store.state.election.planList;
        }
    },
    filters:{
        /**
         * @function 截取年份
         * @param {模板中的插值} val
         * @return {返回年份}
         */
        getYear(val){
            return val.toString().substring(0,4);
        },

        /**
         * @function 从学期名称字符串中获取学期数字(如第1学期中的1),且转化为中文
         * @param {学期名称}
         * @return {返回中文数字}
         */
        getTermValue(str){
            if(str.includes('一') || str.includes('二')){
                return str.substring(str.indexOf("一"),str.indexOf("一")+1);
            }
            let cnNums = ['一','二','三','四','五','六','七','八','九','十'];    
            return cnNums[parseInt(str.slice(-3,-2))-1]
        }
    },
    methods:{
        ...mapMutations('election',['SET_CURRENT_PLAN_ID','SET_CURRENT_TERM_ID']),
        /**
         * @function 监听点击某计划事件，然后记录当前被选择计划Id
         * @param {计划显示序号} index
         * @param {计划id} planId
         * @param {学期id} termId
         */
        onClick(index,planId,termId){
            this.actIdx = index;
            //this.$router.push({name:'AddElectivePlan',query:{planId}})
            this['SET_CURRENT_PLAN_ID'](planId);
            this['SET_CURRENT_TERM_ID'](termId);
        },
        /**@function 根据浏览器窗口宽度大小，自动调整选课计划显示数量 */
        setMaxShowItems(){
            let clientWidth = document.body.clientWidth;        
            if(clientWidth>1920)
                this.planNums = 8;
            else if(clientWidth>=1640)
                this.planNums = 4;
            else if(clientWidth > 1400)
                this.planNums = 3;
            else if(clientWidth >= 1120)
                this.planNums = 2;
            else if(clientWidth >= 1024)
                this.planNums = 1;        
        },
        /**
         * @function 删除选课计划
         * @param {选课计划对象} plan
         */
        removePlan(plan){
            this.$confirm('确认删除本选课计划吗?', '提示', {
				confirmButtonText: '确定',
				cancelButtonText: '取消',
				type: 'warning'
            })
            .then(async () => {
                if(plan.state != 4 || plan.state != 1)
                    await changePlanState({//更改选课计划状态为完成
                        xkpId:plan.xkpId,
                        xkpState:4//选课计划状态 1未开启 2开启(默认) 3关闭 4完成
                    })
                delElectPlans({xkpIds:plan.xkpId})
                    .then(res => {
                        if(res.data.success){
                            this.$message.success(res.data.message);
                            this.getPlanList();
                        }else{
                            if(res.data.type === 1)
                                this.$message.error(res.data.message)
                            else{
                                this.$message.error('发生未知错误，请联系管理员！');
                                console.log(res.data.message);
                            }
                        }
                    })
                    .catch(err => {
                        xhrErrHandler(err,this.$router,this.$message)
                    })

            })
            .catch(() => {
                this.$message.info('已取消删除')
            })
        },
        /**@function 获取选课计划列表 */
        getPlanList(){
            this.$store.dispatch('election/getElectPlanList',{orderFd:'@planName'})
                .then(res => {
                    if(res.success){
                        //this.plans = res.dataList.reverse();
                        if(this.plans.length>0){//当前计划列表非空时      
                            this['SET_CURRENT_PLAN_ID'](this.plans[0].xkpId);
                            this['SET_CURRENT_TERM_ID'](this.plans[0].termId);
                        }
                    }else{
                        this.$message(res.message)
                    }
                })
                .catch(err => {
                    xhrErrHandler(err,this.$router,this.$message);
                })
        }
    },
    mounted(){
        this.getPlanList();
        window.onresize = this.setMaxShowItems;//监听浏览窗口大小变化，自动调整选课计划显示数量   
        this.setMaxShowItems();//默认情况下，显示数量        
    }
}
</script>

<style>
    .term-plan .el-button--mini.is-circle{
        padding:2px!important;
        color:#ff7a7b;
        position:absolute;
        right:-6px;
        top:-6px;
    }
</style>


<style scoped>

    .term-plan{
        height: 96px;
        background-color: #edf2f6;
        border-radius: 3px;
        color: #707079;
        padding:16px 30px;
        width:206px;
        box-sizing:border-box;
        font-family: 'PingFang-SC-Medium';
        float:left;
        margin-right:40px;
        position: relative;
        cursor: pointer;
    }
    .term-txt{
        font-size:48px; 
        float:left;     
        margin-right:5px;  
    }
    .term-border{
        width:22px;
        padding:5px 0;
        margin-top:5px;
        font-size:14px;
        border:1px solid #707079;
        border-radius:4px;
        float:left;
    }
    .term{
        clear:left;
        text-align:left;
        line-height:2em;
        font-size:14px;
    }
    .active{
        background-color:#0d714a;
        color:#fff;
    }
    .activeTerm{
        border:1px solid #fff;
    }
    .plan-done{
        position:absolute;
        top:0;
        right:0;
    }
</style>
