<template>
     <div class="teaching-site-manage">
        <div style="height:8%">
        <add-site-popup></add-site-popup>   <!--这是新增场地-->
        <set-building></set-building>  <!--这是设置教师楼-->
        <by-building-code></by-building-code> <!--教学楼编号-->
        <by-building-name></by-building-name> <!--这是教学楼名称1-->
        <by-buildingTeacher-style></by-buildingTeacher-style> <!--这是教师类型-->
        <by-building-name2></by-building-name2> <!--这是教学楼名称2-->
        <by-building-nameSearch></by-building-nameSearch> <!--这是教师姓名搜索-->
      </div> 
        <teaching-table></teaching-table> <!--这是教学场地表格-->
      </div>
</template>

<script>
import AddSitePopup from './add-site-popup'
import TeachingTable from './teaching-table' 
import SetBuilding from './set-building'
import ByBuildingCode from './filters/by-building-code'
import ByBuildingName from './filters/by-building-name'
import ByBuildingTeacherStyle from './filters/by-buildingTeacher-style'
import ByBuildingName2 from './filters/by-building-name2'
import ByBuildingNameSearch from './filters/by-building-nameSearch'

export default {
    components:{
       TeachingTable,
       AddSitePopup,
       SetBuilding,
       ByBuildingCode,
       ByBuildingName,
       ByBuildingTeacherStyle,
       ByBuildingName2,
       ByBuildingNameSearch,
       
    },
    
    
    data(){
        return{

        }   
    },
    methods:{

    },
    created(){

    },
    mounted(){

    }
}
</script>

<style scoped>
body,html{ /*表格背景色 */
  height: 100%;
}
.teaching-site-manage{ /*表格背景色 */
  background: white; 
  width: 100%;
  height: 100%;
}

</style>