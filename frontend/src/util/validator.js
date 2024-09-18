export class Validator {
    constructor(value, message) {
      this.value = value || '';  // undefined일 경우 빈 문자열로 처리
      this.message = message;
    }
  
    isNotEmpty() {
      if (this.value.trim().length === 0) {
        throw new Error(this.message);
      }
      return this;
    }
  
    matches(regex, regexMessage) {
      if (!regex.test(this.value)) {
        throw new Error(regexMessage);
      }
      return this;
    }
  
    isEqualTo(valueToCompare, errorMessage) {
      if (this.value !== valueToCompare) {
        throw new Error(errorMessage);
      }
      return this;
    }
  }