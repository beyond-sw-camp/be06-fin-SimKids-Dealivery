<template>
  <HeaderComponent></HeaderComponent>
  <div id="container">
    <div id="main">
      <div id="content">
        <div class="page_aticle aticle_type2">
          <CompanyAsideComponent></CompanyAsideComponent>
          <CompanyBoardPostComponent :data="data"></CompanyBoardPostComponent>
        </div>
      </div>
    </div>
  </div>
  <FooterComponent></FooterComponent>
</template>

<script>
import CompanyAsideComponent from "@/components/company/CompanyAsideComponent.vue";
import CompanyBoardPostComponent from "@/components/company/CompanyBoardPostComponent.vue";
import HeaderComponent from "../../../components/common/HeaderComponent.vue";
import FooterComponent from "../../../components/common/FooterComponent.vue";
import { mapStores } from "pinia";
import { useBoardStore } from "../../../stores/useBoardStore";

export default {
  name: "CompanyBoardPostPage",
  components: {
    HeaderComponent,
    FooterComponent,
    CompanyAsideComponent,
    CompanyBoardPostComponent,
  },
  data() {
    return {
      idx: null,
      data: null,
    };
  },
  computed: {
    ...mapStores(useBoardStore),
  },
  created() {
    if (this.$route.params.idx) {
      this.idx = this.$route.params.idx;
      this.data = this.getBoardDetail();
    }
  },
  methods: {
    async getBoardDetail() {
      this.data = await this.boardStore.getProductBoardDetail(this.idx);
    },
  },
};
</script>

<style scoped>
#content {
  min-width: 1050px;
}

.page_aticle {
  width: 1050px;
  margin: 0 auto;
}

.page_aticle.aticle_type2 {
  padding-top: 65px;
}

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
