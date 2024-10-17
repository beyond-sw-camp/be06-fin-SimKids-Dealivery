<template>
  <div class="css-ikeoj9">
    <!--메인 썸네일-->
    <div class="css-p55uhh" style="border-radius: 6px">
      <div class="css-0">
        <div class="css-pw7jst" style="padding-bottom: 128.372%">
          <div class="css-j2pfbl">
            <span class="thumnail-wrapper">
              <img
                :alt="`Image ${mainThumbnail}`"
                :src="thumbnails[index]"
                decoding="async"
                data-nimg="fill"
                class="css-1zjvv7 thumnail-img"
                :style="imageStyle"
                sizes="100vw"
              />
            </span>
          </div>
        </div>
      </div>
    </div>
    <!-- 썸네일 컨테이너 -->
    <div class="thumnail-container">
      <div
        v-for="(thumbnail, index) in thumbnails"
        :key="index"
        class="thumnail-item"
        @click="updateMainThumbnail(index)"
      >
        <div class="css-0">
          <div class="css-pw7jst" style="padding-bottom: 128.372%">
            <div class="css-j2pfbl">
              <span class="thumnail-wrapper">
                <img
                  :alt="`Thumbnail ${thumbnail}`"
                  :src="thumbnail"
                  decoding="async"
                  data-nimg="fill"
                  class="css-1zjvv7 thumnail-img"
                  :style="imageStyle"
                  :sizes="thumbnail.sizes"
                />
              </span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "BoardDetailThumnailComponent.vue",
  props: {
    thumbnails: {
      type: Array,
      required: true,
    },
  },
  data() {
    return {
      mainThumbnail: this.thumbnails[0],
      index: 0,
    };
  },
  methods: {
    updateMainThumbnail(index) {
      // this.mainThumbnail = thumbnail;
      this.index = index;
    },
  },
};
</script>
<style scoped>
.css-ikeoj9 {
  width: 430px;
}

.css-p55uhh {
  overflow: hidden;
  border-radius: 4px;
}

.css-pw7jst {
  position: relative;
  width: 100%;
}

.css-j2pfbl {
  position: absolute;
  inset: 0px;
}

.css-1zjvv7 {
  user-select: none;
  -webkit-user-drag: none;
}

.thumnail-container {
  display: flex;
  gap: 10px;
  width: 430px;
  text-align: center;
  margin-top: 20px;
}

.thumnail-item {
  overflow: hidden;
  border-radius: 6px;
  width: 100px;
  height: 100px;
}

.thumnail-img {
  position: absolute;
  inset: 0px;
  box-sizing: border-box;
  padding: 0px;
  border: none;
  margin: auto;
  display: block;
  width: 0px;
  height: 0px;
  min-width: 100%;
  max-width: 100%;
  min-height: 100%;
  max-height: 100%;
  object-fit: cover;
  cursor: pointer;
}

.thumnail-wrapper {
  box-sizing: border-box;
  display: block;
  overflow: hidden;
  width: initial;
  height: initial;
  background: none;
  opacity: 1;
  border: 0px;
  margin: 0px;
  padding: 0px;
  position: absolute;
  inset: 0px;
}
</style>
