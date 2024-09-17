<script setup>
import { ref, defineEmits, defineProps } from "vue";
import { useBoardStore } from "../../stores/useBoardStore";

const uploadedImages = ref([]);
const previewImages = ref([]);
const emit = defineEmits(["updateContent"]);
const props = defineProps({
  maxImages: {
    type: Number,
    default: 1,
  },
  isActivate: {
    type: Boolean,
    default: true,
  },
});

const boardStore = useBoardStore();

// 이미지 업로드 처리 함수
const handleImageUpload = (event) => {
  const files = event.target.files;
  // 이미지 개수 제한 체크
  if (
    (props.maxImages === 8
      ? boardStore.getThumbnailUrlSize()
      : boardStore.getDetailUrlSize()) +
      uploadedImages.value.length +
      files.length >
    props.maxImages
  ) {
    alert(
      `등록할 수 있는 이미지의 개수를 초과했습니다. 최대 ${props.maxImages}개까지만 등록할 수 있습니다.`
    );
    return;
  }

  // 새로 업로드된 파일을 처리
  for (let i = 0; i < files.length; i++) {
    const file = files[i];
    const id = Date.now() + i;

    uploadedImages.value.push({ id: id, file: file });

    const reader = new FileReader();
    reader.onload = (e) => {
      previewImages.value.push({ id: id, src: e.target.result });
    };

    reader.readAsDataURL(file);
  }

  emit("updateContent", {
    images: uploadedImages.value,
  });
};

const removeImageUrl = (url) => {
  if (props.maxImages === 8) {
    const thumbnailArr = boardStore
      .getThumbnailUrls()
      .filter((elem) => elem !== url);
    boardStore.setThumbnailUrls(thumbnailArr);
  } else if (props.maxImages === 1) {
    boardStore.resetDetailUrl();
  }
};

// 이미지 삭제 처리 함수
const removeImage = (id) => {
  uploadedImages.value = uploadedImages.value.filter(
    (image) => image.id !== id
  );
  previewImages.value = previewImages.value.filter((image) => image.id !== id);

  emit("updateContent", {
    images: uploadedImages.value,
  });
};
</script>

<template>
  <div class="image-upload-container">
    <input
      :id="props.maxImages"
      name="media"
      type="file"
      multiple
      accept="image/png, image/jpeg, image/jpg"
      class="hidden"
      @change="handleImageUpload"
      :disabled="!props.isActivate"
    />
    <label
      :for="props.maxImages"
      :class="['product-image-btn', { 'disabled-label': !props.isActivate }]"
    >
      <div class="flex flex-col">
        <img src="../../assets/image_upload.svg" />
        <p class="image-cnt">
          {{
            (props.maxImages === 8
              ? boardStore.getThumbnailUrlSize()
              : boardStore.getDetailUrlSize()) + uploadedImages.length
          }}
          /
          {{ props.maxImages }}
        </p>
      </div>
    </label>
    <div class="image-preview-container">
      <div
        v-for="image in props.maxImages === 8
          ? boardStore.getThumbnailUrls()
          : boardStore.getDetailUrl()"
        :key="image"
        class="image-preview"
      >
        <img :src="image" alt="Uploaded Image" />
        <button
          :disabled="!props.isActivate"
          @click="removeImageUrl(image)"
          class="remove-image-btn"
        >
          X
        </button>
      </div>
      <div v-for="image in previewImages" :key="image.id" class="image-preview">
        <img :src="image.src" alt="Uploaded Image" />
        <button
          :disabled="!props.isActivate"
          @click="removeImage(image.id)"
          class="remove-image-btn"
        >
          X
        </button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.image-upload-container {
  display: flex;
  padding-left: 1.25rem;
  padding-right: 1.25rem;
  padding-bottom: 0.375rem;
  margin-bottom: 15px;
  margin-top: 15px;
}

.product-image-btn {
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100px;
  height: 100px;
  margin-right: 0.375rem;
  background: rgb(241 244 246);
  border: 2px solid rgb(241 244 246);
  border-radius: 0.25rem;
}

.disabled-label {
  cursor: not-allowed;
  opacity: 0.5; /* 비활성화된 것처럼 보이게 추가적으로 흐리게 처리 */
}

.image-cnt {
  margin-top: 0.25rem;
  font-size: 16px;
  line-height: 1rem;
  color: rgb(156 163 175 / var(--tw-text-opacity));
}

.image-preview-container {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  max-width: 450px;
}

.image-preview {
  position: relative;
  width: 100px;
  height: 100px;
  border: 2px solid rgb(241 244 246);
  border-radius: 5px;
}

.image-preview img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 5px;
}

.remove-image-btn {
  position: absolute;
  top: 5px;
  right: 5px;
  background-color: rgba(0, 0, 0, 0.7);
  color: white;
  border: none;
  border-radius: 50%;
  width: 20px;
  height: 20px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
}

.price-input-container {
  display: flex;
  flex-direction: column;
  margin-bottom: 40px;
  max-width: 200px;
}

label {
  margin-bottom: 0.5rem;
  font-weight: bold;
}

.input-wrapper {
  display: flex;
  align-items: center;
  border: 1px solid #ccc;
  border-radius: 0.25rem;
  padding: 0.5rem;
  background-color: #f9f9f9;
}

.currency-symbol {
  font-size: 1rem;
  margin-right: 0.25rem;
}

input {
  border: none;
  outline: none;
  flex: 1;
  font-size: 1rem;
  padding: 0.25rem;
  background: transparent;
}

input::placeholder {
  color: #999;
}

input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}

input[type="number"] {
  -moz-appearance: textfield;
}

.flex {
  display: flex;
}

.flex-col {
  flex-direction: column;
}

.hidden {
  display: none;
}

button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}

input:disabled {
  cursor: not-allowed;
}

.disabled-label {
  cursor: not-allowed;
  opacity: 0.5; /* 선택적: 비활성화된 느낌을 줄 수 있음 */
}
</style>
