<template>
  <div id="container">
    <div id="main">
      <div id="content">
        <div class="page_aticle aticle_type2">
          <CompanyAsideComponent></CompanyAsideComponent>
          <CompanyBoardPostComponent></CompanyBoardPostComponent>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import CompanyAsideComponent from "@/components/company/CompanyAsideComponent.vue";
import CompanyBoardPostComponent from "@/components/company/CompanyBoardPostComponent.vue";
export default {
  name: "MainPage",
  components: {
    CompanyAsideComponent,
    CompanyBoardPostComponent,
  },
};
</script>

<style scoped>
#content {
  min-height: 1800px;
  min-width: 1050px;
}

.page_aticle {
  width: 1050px;
  margin: 0 auto;
}

.page_aticle.aticle_type2 {
  padding-top: 65px;
}

/* #snb {
  float: left;
  width: 200px;
} */

/* #snb .tit_snb {
  padding: 8px 0 33px 1px;
  font-weight: 700;
  font-size: 30px;
  line-height: 34px;
  color: #333;
  letter-spacing: -0.5px;
}

#snb .inner_snb {
  border: 1px solid #f2f2f2;
  border-bottom: 0;
}

#snb .list_menu li {
  border-bottom: 1px solid #f2f2f2;
} */

/* #snb .list_menu li.on a,
#snb .list_menu li a:hover {
  background: #fafafa
    url(https://res.kurly.com/pc/ico/2008/ico_arrow_6x11_on.svg) no-repeat 174px
    52%;
  background-size: 6px 11px;
  font-weight: 700;
  color: #5f0080;
} */

/* #snb .list_menu li a {
  display: block;
  overflow: hidden;
  padding: 15px 0 15px 20px;
  background: #fff url(https://res.kurly.com/pc/ico/2008/ico_arrow_6x11.svg)
    no-repeat 174px 52%;
  background-size: 6px 11px;
  font-size: 14px;
  color: #666;
  line-height: 20px;
  letter-spacing: -0.3px;
}

.snb_my {
  top: 220px;
  left: 400px;
  margin-right: -670px;
} */

.page_aticle.aticle_type2 .page_section {
  float: right;
  width: 820px;
}

.page_aticle .head_aticle {
  padding: 5px 0 34px;
}

.page_aticle .head_aticle .tit {
  height: 36px;
  font-weight: 700;
  font-size: 24px;
  line-height: 36px;
  color: #333;
  letter-spacing: -0.5px;
  display: inline-block;
}

body,
div,
dl,
dt,
dd,
ul,
ol,
li,
h1,
h2,
h3,
h4,
h5,
h6,
form,
fieldset,
legend,
input,
button,
textarea,
p,
blockquote,
th,
td,
a,
span {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
</style>
