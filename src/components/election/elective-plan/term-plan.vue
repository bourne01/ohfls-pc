<template>
    <div class="term-plans">
        <div class="term-plan" 
            :class="{active:idx===actIdx}"
            @click="actIdx=idx"
            v-for="(term,idx) in termList" :key="idx" v-if="idx<planNums">
            <div>
                <span class="term-txt">{{term.termName|getYear}}</span>
                <span 
                    class="term-border"
                    :class="{activeTerm:idx===actIdx}"
                >学年</span>
            </div>
            <div class="term">第{{term.termName|getTermValue}}学期</div>
            <img 
                class="plan-done" 
                :src="require('../../../assets/completed.png')" alt=""
                v-if="idx!==0">
        </div>
    </div>
    
</template>

<script>
import { getTerms } from '../../../api/public.js'
import { xhrErrHandler } from '../../../utils/util.js'
export default {
    props:['idx'],
    data(){
        return{
            actIdx:0,//默认最新学期激活
            termList:[],//学期列表
            planNums:0,//屏幕展示学科计划数量，由分辨率而定
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
            let cnNums = ['一','二','三','四','五','六','七','八','九','十'];            
            console.log(parseInt(str.slice(-3,-2))-1)
            return cnNums[parseInt(str.slice(-3,-2))-1]
        }
    },
    mounted(){
        /**获取学期列表 */
        getTerms()
            .then(res => {
                console.log(res)
                if(res.data.success){
                    this.termList = res.data.dataList.reverse();
                    console.log(this.termList);
                }else{
                    this.$message(res.message)
                }
            })
            .catch(err => {
                xhrErrHandler(err,this.$message);
            })
        /**根据屏幕分辨率判定界面显示及格选课计划 */
        let screenWidth = window.screen.width;        
        if(screenWidth>1920)
            this.planNums = 8;
        else if(screenWidth>=1400)
            this.planNums = 4;
        else if(screenWidth > 1366)
            this.planNums = 3;
        else if(screenWidth >= 1280)
            this.planNums = 2;
        else if(screenWidth >= 1024)
            this.planNums = 1;        
    }
}
</script>


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
