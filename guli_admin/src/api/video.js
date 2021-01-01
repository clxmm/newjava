import request from '@/utils/request'

export default {

    save(video) {
        return request({
            url: '/admin/edu/video/save',
            method: 'post',
            data: video
        })
    },

    update(video) {
        return request({
            url: '/admin/edu/video/update',
            method: 'put',
            data: video
        })
    },

    getVideoById(id) {
        return request({
            url: `/admin/edu/video/getVideoById/${id}`,
            method: 'get'
        })
    },

    deleteVideoById(id) {
        return request({
            url: `/admin/edu/video/deleteVideoById/${id}`,
            method: 'delete'
        })
    },


    removeByVodId(id) {
        return request({
            baseURL: 'http://127.0.0.1:8130',
            url: `/admin/vod/media/remove/${id}`,
            method: 'delete'
        })
    }


}