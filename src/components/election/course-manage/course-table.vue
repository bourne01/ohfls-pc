<template>
	<div class="course-manage-table">                                                                   
	<el-table    
		ref="multipleTable"
		:data="courseTable"
		tooltip-effect="dark"
		style="width: 100%;"
		@selection-change="handleSelectionChange"
		max-height:610px
		border>                                               <!--表格主题--> 
		<el-table-column                                              
		type="selection"
		width="70"
		>                                                      
		</el-table-column>                                    <!--复选框列-->
		<el-table-column
		label="课程名称"
		align="center"
		min-width="200"
		prop="couName">
		</el-table-column>                       <!--课程名称列--> 
		<el-table-column    
		label="子课程"
		min-width="70"      
		>
		<template slot-scope="scope">
		<el-button type="text" size="small">
		{{scope.row.chiNum}}</el-button>
		</template>         
		</el-table-column>                                                       <!--子课程列--> 
		<el-table-column
		label="授课场地"
		>
		<template slot-scope="scope">
			{{scope.row.romName|isNull}}
		</template>
		</el-table-column>
		<el-table-column
		label="授课教师">
		<template slot-scope="scope" >
			<my-dropdown :items="scope.row.couThrNms"></my-dropdown>                                             
		</template>         
		</el-table-column>                           <!--授课教师列--> 
		<el-table-column
		label="适用年级"
		>
		<template slot-scope="scope" >
			<my-dropdown :items="scope.row.gradeNms"></my-dropdown>                                             
		</template>      
		</el-table-column>
		<el-table-column
		label="班级限额"
		prop="manMax">
		</el-table-column>
		<el-table-column
		label="人数"
		min-width="100">
		<template slot-scope="scope">                           
			<add-to-course :course-id="scope.row.selCouId"></add-to-course>
		</template>         
		</el-table-column>
		<el-table-column
		prop="couState"
		label="课程状态"
		width="180"
		:filters="stateFilter"
		:filter-method="filterstate"
		filter-placement="bottom-end">
		<template slot-scope="scope">     
			<course-state  :current-course="scope.row" ></course-state> <!--修改课程状态的列-->
		</template>
		</el-table-column>
		<el-table-column
		label="操作"
		min-width="170">
		<template slot-scope="scope"> 
			<actions :current-course="scope.row"></actions>
		</template>      
		</el-table-column>                        <!--操作列-->
  	</el-table>
  	<div class="footer">
		<el-checkbox @change="toggleSelection(courseTable)" class="select-all">全选</el-checkbox>         <!--选中当前全部表格行的复选框-->
		<div class="amount">(当前页共条)</div>                                           <!---->
		<el-button class="footer-button" style="margin-left:2.2%;color:#ff7a7b">批量删除</el-button>
		<el-button class="footer-button" style="margin-left:1.2%;color:rgb(93,156,253)" >批量审核</el-button>
		<el-button class="footer-button" style="margin-left:1.2%;color:#14b25a">批量确认</el-button>
		<el-pagination
		background
		layout="prev, pager, next"
		:total="140">
		:page-size="8"
		</el-pagination>    <!--翻页的-->
	</div>
  </div>
</template>

<script>
import CourseState from './course-state.vue'
import addToCourse from './students-manage/add-to-course.vue'
import MyDropdown from './dropdown'
import Actions from './actions'
import {mapActions,mapState} from 'vuex'
export default {
  components:{
    addToCourse,
    CourseState,
    MyDropdown,
    Actions,
  },
  data() {
    return {
      isPop:false,
      stateFilter:[ { text: '待审核', value: 1 },
          {text:'确认选课',value:2},
          { text: '结束上课', value: 3 },
          { text: '确认', value: 4 },
          { text: '已完成', value: 5 },] 
    }
  },

  methods: {
  /**@function 提交选课计划*/
      submit(){
          console.log(this.course);
          this.$router.push('/home/election')
      },
    toggleSelection(rows) {   /* 全选按钮*/
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
      formatter(row, column) {
      return row.courseName;
    },
    filterstate(value, row) {
      return row.state === value;
    },
    handleCommand(command) {
      this.$message('click on item ' + command);
  },
  
    ...mapActions('election',['getCourseTable','getElectResultList'])
  },
  filters:{
    isNull:function(val){
      if(!val)
        return '未设置'
      else
        return val
    }
},
watch:{
  //监听当前计划Id的变化，则更新课程列表
  currentPlanId:function(){
    let url = 'selCou!query2.action';
    let params = {start:0,limit:10,parCouId:-1,xkpId:this.currentPlanId};
    this.getCourseTable({url,params})
  }
},
  computed:{
...mapState('election',{
    courseTable:state => state.courseTable,
    currentPlanId:state => state.currentPlanId
    })
  },
  mounted(){
    /* let url = 'selCou!query2.action';
    let params = {start:0,limit:10,parCouId:-1};
    this.getCourseTable({url,params}) */
  }

}

</script>


<style  scoped>
.footer{          /*底部样式*/
  width: 100%;
  height: 20px;
  line-height: 20px;
  
}
.amount{          /*计数样式*/
  float: left;
  font-family: 'Simsun';
  font-size: 12px;
  color: #707079;
  margin-left: 10px;
}
.footer-button{   /*底部按钮的全局样式*/
  float: left;
  margin-top: 12px;
  width: 88px;
  height: 28px;
  line-height: 32%;
  font-size: 12px;
  
}
.el-pagination{   /*翻页按钮样式*/
  float: right;
  margin-top: 10px;
}
.teacher-list{
  float: right;
  margin: 8px 4% 0 0;
}
.course-manage-table{
  height:56vh;
  background-color:#E9EEF3;
}
.footer{          /*底部样式*/
  width: 100%;
  height: 50px;
  box-sizing: border-box;
  line-height: 50px;
  
}
.select-all{      /*全选样式*/
  float: left;
  margin-left: 1.45%
}
.amount{          /*计数样式*/
  float: left;
  font-family: 'Simsun';
  font-size: 12px;
  color: #707079;

}
.footer-button{   /*底部按钮的全局样式*/
  float: left;
  margin-top: 12px;
  width: 88px;
  height: 28px;
  line-height: 32%;
  font-size: 12px;
  
}
.el-pagination{   /*翻页按钮样式*/
  float: right;
  margin-top: 8px;
}
</style>


<style>
  .course-manage-table th{       /*  表头字体居中 */
  text-align: center;
  background-color:#f6f9fb;
  color: #2b2b32;
  }
  .course-manage-table .el-table-filter__content{      /*筛选框的宽度*/
    min-width: 146px;
  }
  .course-manage-table .el-checkbox-group.el-table-filter__checkbox-group{     /*筛选框的样式*/
      padding-left:20px;
  }
  .course-manage-table .el-pagination.is-background .el-pager li:not(.disabled).active {  /*翻页的背景色*/
      background-color:rgb(13,113,74);
  }
  .course-manage-table .el-table__column-filter-trigger i{
  margin-left: 3px;
  font-size: 16px;
  }
</style>


