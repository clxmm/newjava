<template>
  <div>
    讲师列表
    <div v-for="(teacher,index) in items" :key="index">
      <a href="#">{{ teacher }}</a>
    </div>
  </div>
</template>

<script>
import teahcerApi from '~/api/teacher'
export default {

    asyncData() {
        return teahcerApi.getList().then(res => {
            return {
                items: res.data.item
            }
        })

    }
    
}
</script>