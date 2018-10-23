import http  from 'axios'

const proxyURL = '/p'
const baseURL = proxyURL+'/p'
/***********************************************************************************
 * 待选课程
 ***********************************************************************************/
/**
 * @function 查询课程列表
 */
export const getCourseList = (url,params) => {
    return http(`${baseURL}/${url}`,{params});
}
/**
 * @function 导出课程信息
 */
export const exportCourse = (params) => {
    return http({
            method:'post',
            url:`${baseURL}/selCou!export.action`,
            data:require('qs').stringify(params),
            responseType: 'blob'
            }
        );
}
/**
 * @function 我的课程列表,使用者：开课教师、合作机构
 */
export const myCourses = (params) => {
    return http(`${baseURL}/selCou!myCou.action`,{params});
}
/**
 * @function 专业委员查询
 */
export const leaderCourses = (params) => {
    return http(`${baseURL}/selCou!memQue.action`,{params});
}
/**
 * @function 新增课程
 */
export const addCourse = (params) => {
    return http({
        method:'post',
        url:`${baseURL}/selCou!newRec.action`,
        data:params})
}
/**
 * @function 编辑课程
 */
export const editCourse = (params) => {
    return http({
        method:'post',
        url:`${baseURL}/selCou!edit.action`,
        data:params});
}
/**
 * @function 删除课程
 */
export const delCourse = (params) => {
    return http(`${baseURL}/selCou!del.action`,{params});
}
/**
 * @function 更改状态
 */
export const changeCouState = (params) => {
    return http(`${baseURL}/selCou!chState.action`,{params})
}
/**
 * @function 上传课程封面(POST) 权限：管理员、课程教师(自己)、课程合作机构(自己)
 */
export const uploadCover = (params) => {
    return http(`${baseURL}/couCover`,{params})
}
/**
 * @function 获取课程封面(GET)
 */
export const getCover = (params) => {
    return http(`${baseURL}/couCover`,{params})
}
/***********************************************************************************
 * 选课计划
 ***********************************************************************************/
/**
 * @function 查询选课计划
 */
export const getElectPlans = (params) => {
    return http(`${baseURL}/xkp!query.action`,{params});
}
/**
 * @function 新增选课计划
 */
export const addElectPlan = (params) => {
    return http(`${baseURL}/xkp!newRec.action`,{params});
}
/**
 * @function 编辑选课计划
 */
export const editElectPlan = (params) => {
    return http(`${baseURL}/xkp!edit.action`,{params});
}
/**
 * @function 删除选课计划
 */
export const delElectPlans = (params) => {
    return http(`${baseURL}/xkp!del.action`,{params});
}
/**
 * @function 更改状态
 */
export const changePlanState = (params) => {
    return http(`${baseURL}/xkp!chState.action`,{params})
}
/***********************************************************************************
 * 选课任务
 ***********************************************************************************/
/**
 * @function 查询选课任务
 */
export const getElectDuties = (url,params) => {
    return http(`${baseURL}/${url}`,{params});
}
/**
 * @function 新增选课任务
 */
export const addElectDuty = (params) => {
    return http(`${baseURL}/xkTask!newRec.action`,{params});
}
/**
 * @function 编辑选课任务（包含修改任务状态）
 */
export const editElectDuty = (params) => {
    return http(`${baseURL}/xkTask!edit.action`,{params});
}
/**
 * @function 删除选课任务
 */
export const delElectDuties = (params) => {
    return http(`${baseURL}/xkTask!del.action`,{params});
}
/***********************************************************************************
 * 选课结果
 ***********************************************************************************/
/**
 * @function 查询选课结果
 */
export const getElectResults = (url,params) => {
    return http(`${baseURL}/${url}`,{params});
}
/**
 * @function 开课教师或合作机构查询选课结果
 */
export const getMyElectResults = (params) => {
    return http(`${baseURL}/stuCou!myCouStu.action`,{params});
}
/**
 * @function 专业委员查询选课结果
 */
export const getLeaderElectResults = (params) => {
    return http(`${baseURL}/stuCou!memQue.action`,{params});
}
/**
 * @function 新增选课结果
 */
export const addElectResult = (params) => {
    return http(`${baseURL}/xkTask!newRec.action`,{params});
}
/**
 * @function 编辑选课结果（包含修改任务状态）
 */
/* export const editElectResult = (params) => {
    return http(`${baseURL}/stuCou!chState.action`,{params});
} */
/**
 * @function 删除选课结果
 */
export const delElectResults = (params) => {
    return http(`${baseURL}/stuCou!del.action`,{params});
}
/**
 * @function 状态控制(选课状态、控制教师评价与学生评价)
 */
export const changeStuCouState = (params) => {
    return http(`${baseURL}/stuCou!chState.action`,{params});
}
/***********************************************************************************
 * 选课名单导出
 ***********************************************************************************/
/**
 * @function 下载学生名单
 */
export const downloadStuList = (url,params) => {
    return http(`${baseURL}/${url}`,{params});
}
/***********************************************************************************
 * 师生互评
 ***********************************************************************************/
/**
 * @function 查询师生互评结果
 */
export const getEvaluations = (url,params) => {
    return http(`${baseURL}/${url}`,{params});
}
/**
 * @function 开课教师或合作机构查询师生互评
 */
export const getMyEvaluations = (params) => {
    return http(`${baseURL}/stuCouEval!myCouEval.action`,{params});
}
/**
 * @function 专业委员查询师生互评
 */
export const getLeaderEvaluations = (params) => {
    return http(`${baseURL}/stuCouEval!memQue.action`,{params});
}
/**
 * @function 更新评价记录 -- 管理级
 */
export const updateThrStuEvaluation = (params) => {
    return http(`${baseURL}/stuCouEval!upRec.action`,{params});
}
/**
 * @function 状态控制
 */
export const changeEvalState = (params) => {
    return http(`${baseURL}/stuCouEval!chState.action`,{params});
}
/**
 * @function 教师评价学生 --使用者：开课教师
 */
export const evaluateStudent = (params) => {
    return http(`${baseURL}/stuCouEval!thrEvalStu.action`,{params});
}
/***********************************************************************************
 * 课程评价
 ***********************************************************************************/
/**
 * @function 查询师课程评价
 */
export const getCouEvaluations = (url,params) => {
    return http(`${baseURL}/${url}`,{params});
}
/**
 * @function 开课教师或合作机构查询课程评价
 */
export const getMyCouEvaluations = (params) => {
    return http(`${baseURL}/stuCouEval!myCouEval.action`,{params});
}
/**
 * @function 专业委员查询课程评价
 */
export const getLeaderCouEvaluations = (params) => {
    return http(`${baseURL}/selCouEval!myCouEval.action`,{params});
}
/**
 * @function 更新评价记录 -- 管理级
 */
export const updateCouEvaluation = (params) => {
    return http(`${baseURL}/selCouEval!upRec.action`,{params});
}
/**
 * @function 教师自评、机构评价 -- 使用者：开课教师、合作机构
 */
export const selfEvaluate = (params) => {
    return http(`${baseURL}/selCouEval!myEval.action`,{params});
}
/**
 * @function 教师自评、机构评价 -- 使用者：开课教师、合作机构
 */
export const leaderEvaluate = (params) => {
    return http(`${baseURL}/stuCouEval!memEval.action`,{params});
}
/***********************************************************************************
 * 学生和课程
 ***********************************************************************************/
/**
 * @function 查询学生与待选课程的关系。课程可被那些学生选或学生可以选什么课程 
 */
export const getStuAndCouRelations = (params) => {
    return http(`${baseURL}/stuAndCou!query.action`,{params})
}
/***********************************************************************************
 * 学生选课状态
 ***********************************************************************************/
/**
 * @function 查询学生选课状态
 */
export const getStuElectState = (params) => {
    return http(`${baseURL}/stuXkState!query.action`,{params})
}
/**
 * @function 导出学生选课状态
 */
export const exportStuElectState = (params) => {
    return http(`${baseURL}/stuXkState!export.action`,{params})
}
