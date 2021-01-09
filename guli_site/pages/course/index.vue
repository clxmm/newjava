<template>
  <div id="aCoursesList" class="bg-fa of">
    <!-- 课程列表 开始 -->
    <section class="container">
      <header class="comm-title">
        <h2 class="fl tac">
          <span class="c-333">全部课程</span>
        </h2>
      </header>
      <section class="c-sort-box">
        <section class="c-s-dl">
          <dl>
            <dt>
              <span class="c-999 fsize14">课程类别</span>
            </dt>
            <dd class="c-s-dl-li">
              <!-- 一级类别 开始-->
              <ul class="clearfix">
                <li :class="{current:!$route.query.subjectParentId}">
                  <a title="全部" 
                     href="javascript:void(0);"
                     @click="searchSubjectLevelOne('')">全部</a>
                </li>
                <li v-for="(sub,index) in subjectList" :key="index" :class="{current:$route.query.subjectParentId===sub.id}">
                  <a
                    :title="sub.title"
                    href="javascript:void(0);"
                    @click="searchSubjectLevelOne(sub.id)">{{ sub.title }}</a>
                </li>
              </ul>
              <!-- /一级类别 结束-->
            </dd>
          </dl>
          <dl>
            <dt>
              <span class="c-999 fsize14"/>
            </dt>
            <dd class="c-s-dl-li">
              <!-- 二级类别 开始-->
              <ul v-if="$route.query.subjectParentId" class="clearfix">
                <li :class="{current:!$route.query.subjectId}">
                  <a
                    title="全部"
                    href="javascript:void(0);"
                    @click="searchSubjectLevelTwo('')">全部</a>
                </li>
                <li v-for="item in subSubjectList" :key="item.id"
                    :class="{current:$route.query.subjectId===item.id}">
                  <a
                    title="Java"
                    href="javascript:void(0);"
                    @click="searchSubjectLevelTwo(item.id)">{{ item.title }}</a>
                </li>

              </ul>
              <!-- /二级类别 结束-->
            </dd>
          </dl>
          <div class="clear"/>
        </section>
        <div class="js-wrap">
          <section class="fr">
            <span class="c-ccc">
              <i class="c-master f-fM">1</i>/
              <i class="c-666 f-fM">1</i>
            </span>
          </section>
          <section class="fl">
            <!-- 排序 开始-->
            <ol class="js-tap clearfix">
              <li :class="{'current bg-green': $route.query.buyCountSort}">
                <a title="销量" href="javascript:void(0);" @click="searchByCount">销量
                  <i>↓</i>
                </a>
              </li>
              <li :class="{'current bg-green': $route.query.gmtCreateSort}">
                <a title="最新" href="javascript:void(0);" @click="searchGmtCreate">最新
                  <span>↓</span>
                </a>
              </li>
              <li :class="{'current bg-green': $route.query.priceSort}">
                <a title="价格" href="javascript:void(0);" @click="searchPrice">价格
                  <span>↓</span>
                </a>
              </li>
            </ol>
            <!-- /排序 结束-->
          </section>
        </div>
        <div class="mt40">
          <!-- /无数据提示 开始-->
          <section class="no-data-wrap">
            <em class="icon30 no-data-ico">&nbsp;</em>
            <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
          </section>
          <!-- /无数据提示 结束-->

          <!-- 数据列表 开始-->
          <article class="comm-course-list">
            <ul id="bna" class="of">
              <li v-for="item in courseList" :key="item.id">
                <div class="cc-l-wrap">
                  <section class="course-img">
                    <img :alt="item.title" :src="item.cover" class="img-responsive">
                    <div class="cc-mask">
                      <a :href="`/course/`+item.id" title="开始学习" class="comm-btn c-btn-1">开始学习</a>
                    </div>
                  </section>
                  <h3 class="hLh30 txtOf mt10">
                    <a :href="`/course/`+item.id" :title="item.title" class="course-title fsize18 c-333">{{ item.title }}</a>
                  </h3>
                  <section class="mt10 hLh20 of">
                    <span v-if="Number(item.price) === 0" class="fr jgTag bg-green">
                      <i class="c-fff fsize12 f-fA">免费</i>
                    </span>
                    <span v-else class="fr jgTag ">
                      <i class="c-orange fsize12 f-fA"> ￥ {{ item.price }}</i>
                    </span>
                    <span class="fl jgAttr c-ccc f-fA">
                      <i class="c-999 f-fA">{{ item.viewCount }}人学习</i>
                      |
                      <i class="c-999 f-fA">{{ item.buyCount }}人购买</i>
                    </span>
                  </section>
                </div>
              </li>
              
            </ul>
            <div class="clear"/>
          </article>
          <!-- /数据列表 结束-->
        </div>
      </section>
    </section>
    <!-- /课程列表 结束 -->
  </div>
</template>
<script>
import courseApi from '~/api/course'
import querystring from 'querystring' // url参数拼接工具
export default {

  async asyncData(page) {
    

    // 组装查询参数
    const searchObj = {}
    const query = page.route.query
    searchObj.subjectParentId = query.subjectParentId || ''
    searchObj.subjectId = query.subjectId || ''
    searchObj.buyCountSort = query.buyCountSort || ''
    searchObj.gmtCreateSort = query.gmtCreateSort || ''
    searchObj.priceSort = query.priceSort || ''
    
    // 课程列表
    const courseListResponse = await courseApi.getList(searchObj)
    const courseList = courseListResponse.data.item
    // 课程分类
    const subjectResponse = await courseApi.getSubjectList();
    const subjectList = subjectResponse.data.item
    // console.log('courseList: ' + courseList)
    // 遍历一级分类
    let subSubjectList = []
    for (let i = 0; i < subjectList.length; i++) {
      // 如果查询参数中的一级分类id和当前一级分类id一致
      if (subjectList[i].id === searchObj.subjectParentId) {
        // 则获取二级分类列表
        subSubjectList = subjectList[i].children
      }
    }
    return {
      courseList,
      subjectList,
      subSubjectList, // 二级分类列表
      searchObj
    }

  },
  methods: {
    searchSubjectLevelOne(subjectParentId) {
      window.location = 'course?subjectParentId=' + subjectParentId
    },
    // 选择二级分类
    searchSubjectLevelTwo(subjectId) {
      // window.location = 'course?subjectId=' + subjectId + '&subjectParentId=' + this.searchObj.subjectParentId
      
      // 自动组装queryString
       const obj = {
         subjectParentId: this.searchObj.subjectParentId,
         subjectId: subjectId
       }
      const querys = querystring.stringify(obj)
      window.location = '/course?' + querys
    },
    // 销量倒序
    searchByCount() {
        // window.location = 'course?buyCountSort=1'
        // 自动组装queryString
        const obj = {
         subjectParentId: this.searchObj.subjectParentId,
         subjectId: this.searchObj.subjectId,
         buyCountSort: 1
       }
       const querys = querystring.stringify(obj)
       window.location = '/course?' + querys
    },
    searchGmtCreate() {
        // window.location = 'course?buyCountSort=1'
        // 自动组装queryString
        const obj = {
         subjectParentId: this.searchObj.subjectParentId,
         subjectId: this.searchObj.subjectId,
         gmtCreateSort: 1
       }
       const querys = querystring.stringify(obj)
       window.location = '/course?' + querys
    },
    searchPrice() {
        // window.location = 'course?buyCountSort=1'
        // 自动组装queryString
        const obj = {
         subjectParentId: this.searchObj.subjectParentId,
         subjectId: this.searchObj.subjectId,
         priceSort: 1
       }
       const querys = querystring.stringify(obj)
       window.location = '/course?' + querys
    }

  }


}
</script>
