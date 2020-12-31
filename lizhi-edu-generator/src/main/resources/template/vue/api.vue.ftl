import request from '@/utils/request'


//${table.comment}相关接口API

let baseUrl = '/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>'



/**
 * 分页条件查询
 */
export function fetchList(data) {
  return request({
    url: `${r'${baseUrl}'}/${r'${data.page}'}/${r'${data.size}'}`,
    method: 'get',
    params: data
  })
}

/**
 * 添加
 */
export function add${entity}(data) {
  return request({
    url: `${r'${baseUrl}'}`,
    method: 'post',
    data
  })
}

/**
 * 修改
 */
export function edit${entity}(data) {
  return request({
    url: `${r'${baseUrl}'}/${r'${data.id}'}`,
    method: 'PUT',
    data
  })
}

/**
 * 删除
 */
export function delete${entity}(id) {
  return request({
    url: `${r'${baseUrl}'}/${r'${id}'}`,
    method: 'DELETE'
  })
}

/**
 * 批量删除
 * @param ids
 */
export function batchDelete${entity}(ids) {
  return request({
    url: `${r'${baseUrl}'}/batchRemove`,
    method: 'DELETE',
    data: ids
  })
}








