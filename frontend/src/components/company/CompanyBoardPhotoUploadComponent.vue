<script setup>
import { ref, defineEmits, defineProps } from "vue";

const uploadedImages = ref([]);
const previewImages = ref([]);

const emit = defineEmits(["updateContent"]);

const props = defineProps({
  maxImages: {
    type: Number,
    default: 1,
  },
});

const handleImageUpload = (event) => {
  const files = event.target.files;

  if (uploadedImages.value.length + files.length > props.maxImages) {
    alert(
      `등록할 수 있는 이미지의 개수를 초과했습니다. 최대 ${props.maxImages}개 까지만 등록할 수 있습니다.`
    );
    return;
  }

  for (let i = 0; i < files.length; i++) {
    const file = files[i];
    const id = Date.now() + i;

    uploadedImages.value.push({ id: id, file: file });

    const reader = new FileReader();
    reader.onload = (e) => {
      previewImages.value.push({ id: id, src: e.target.result });
    };

    reader.readAsDataURL(files[i]);
  }

  emit("updateContent", {
    images: uploadedImages.value,
  });
};

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
    />
    <label :for="props.maxImages" class="product-image-btn">
      <div class="flex flex-col">
        <img src="../../assets/image_upload.svg" />
        <p class="image-cnt">
          {{ uploadedImages.length }} / {{ props.maxImages }}
        </p>
      </div>
    </label>
    <div class="image-preview-container">
      <div v-for="image in previewImages" :key="image.id" class="image-preview">
        <img :src="image.src" alt="Uploaded Image" />
        <button @click="removeImage(image.id)" class="remove-image-btn">
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
</style>
