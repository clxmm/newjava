import request from '~/utils/request'


export default{

    getList(searchObj) {
        return request({
            url: '/api/edu/course/list',
            method: 'get',
            params: searchObj
    })
    },
    getSubjectList() {
        return request({
            url: '/api/edu/subject/list',
            method: 'get'
        })
    },
    getById(id) {
        return request({
            url: `/api/edu/course/get/${id}`,
            method:'get'
        })
    },
    // 获取视频播放凭证
    getPlayAuth(vid) {
        return request({
            baseURL: 'http://localhost:8130',
            url: `/api/vod/media/get-play-auth/${vid}`,
            method:'get'
        })
    }


}