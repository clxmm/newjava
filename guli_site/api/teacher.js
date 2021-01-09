import request from '~/utils/request'

export default{

    getList() {
        return request({
            url: '/api/edu/teacher/list',
            method: 'get'
        })
    },
    getTeachInfo(id) {
        return request({
            url: `/api/edu/teacher/getTeachInfo/${id}`,
            method:'get'
        })
    }
}