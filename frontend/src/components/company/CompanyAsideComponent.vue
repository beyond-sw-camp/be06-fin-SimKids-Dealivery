<template>
  <div id="container">
    <div id="main">
      <div id="content">
        <div class="page_aticle aticle_type2">
          <div id="snb" class="snb_my">
            <h2 class="tit_snb">판매자 메뉴</h2>
            <div class="inner_snb">
              <ul class="list_menu">
                <li>
                  <a href="#">게시글 등록</a>
                </li>
                <li>
                  <a href="#">게시글 조회</a>
                </li>
                <li>
                  <a href="#">주문 관리</a>
                </li>
                <li>
                  <a href="#">1:1문의 관리</a>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "FooterComponent",
  props: {},
};
</script>

<style scoped>
@import "./css/common.css";
@import "./css/reset.css";
</style>
