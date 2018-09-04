<template>
  <div>
  <div>{{stateText[subState-1]}}</div>
    <button class="sub-course-state" :class="{check:subState===1,confirm:subState===2,class:subState===3,evaluate:subState===4,finish:subState===5}" >
             {{stateTxtList[subState-1]}}
    </button>
    </div>
</template>



<script>
export default {
    props:['subState'],
    data(){
        return{
            stateTxtList:['审核',,,'确认','评价','查看评价'],
            stateText:['课程待审核','已审核，学生选课中···','课程正在进行中···','课程已完成，等待确认记录学分',],
        
        }
    }
};
</script>



<style scoped>
    .sub-course-state{
    width: 26%;
    height:25px;
    border-radius: 2px;
    border: none;
    cursor: pointer;
    
}
  .check{           /*未审核状态的样式*/
    border: 1px solid #448af5;
    background-color: #5b9bfd; 
    color: white;
  }
  .confirm{       /*已审核状态的样式*/
      border: 1px solid #14b25a;
      background-color: #1cd06c;
      display: none;

  }
  .class{           /*正在上课状态的样式*/
    border: 1px solid #d45447;
    background-color: #d45447;
    display: none;
  }
  .evaluate{        /*正在评价状态的样式*/
    border: 1px solid #14b25a;
    background-color: #1cd06c;
    color: white
  }
  .finish{          /*完成状态的样式*/
    border:1px solid #b5b5c0;
    background-color:white; 
    color:black;
    width: 36%
  
  }
</style>


