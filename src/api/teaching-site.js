import http from 'axios'

const proxyURL = '/p'
const baseURL = proxyURL+'/p'
/******************************************************************************************************
 * 教学场地
 *****************************************************************************************************/
/**
 * @function 查询教学场地
 */
export const getTeachingSites = (url,params) => {
    return http(`${baseURL}/${url}`,{params})
}
/**
 * @function 新增教学场地
 */
export const addTeachingSite = (params) => {
    return http(`${baseURL}/rom!newRec.action`,{params})
}
/**
 * @function 编辑教学场地
 */
export const editTeachingSite = (params) => {
    return http(`${baseURL}/rom!edit.action`,{params})
}
/**
 * @function 删除教学场地
 */
export const delTeachingSites = (params) => {
    return http(`${baseURL}/rom!del.action`,{params})
}
/**
 * @function 修改状态记录
 */
export const changeSiteState = (params) => {
    return http(`${baseURL}/rom!chState.action`,{params})
}
/******************************************************************************************************
 * 教学楼
 *****************************************************************************************************/
/**
 * @function 查询教学楼
 */
export const getTeachingBuildings = (url,params) => {
    return http(`${baseURL}/${url}`,{params})
}
/**
 * @function 新增教学楼
 */
export const addTeachingBuilding = (params) => {
    return http(`${baseURL}/bui!newRec.action`,{params})
}
/**
 * @function 编辑教学楼
 */
export const editTeachingBuilding = (params) => {
    return http(`${baseURL}/bui!edit.action`,{params})
}
/**
 * @function 删除教学楼
 */
export const delTeachingBuildings = (params) => {
    return http(`${baseURL}/bui!del.action`,{params})
}
/**
 * @function 修改状态记录
 */
export const changeBuildingState = (params) => {
    return http(`${baseURL}/bui!chState.action`,{params})
}