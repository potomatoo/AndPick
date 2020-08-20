<template>
  <div>
    <div class="back row py-5">
      <ServiceDescription />
      <span @click="test" class="mdi mdi-arrow-down-drop-circle-outline"></span>
    </div>
    <ServiceStep class="service-position" />
  </div>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
import ServiceDescription from "@/components/cover/ServiceDescription.vue";
import ServiceStep from "@/components/cover/ServiceStep.vue";

@Component({
  components: {
    ServiceDescription,
    ServiceStep
  }
})
export default class Cover extends Vue {
  test() {
    const element = document.querySelector(".service-position");
    if (element) {
      const elementTop = element.getBoundingClientRect().top;
      const abTop = window.pageYOffset + elementTop;
      window.scrollTo({ top: abTop - 65, behavior: "smooth" });
    }
  }
}
</script>

<style>
.mdi-arrow-down-drop-circle-outline {
  font-size: 50px;
  position: absolute;
  left: 50%;
  top: 520px;
  cursor: pointer;
}
.back {
  background-size: 100% 100%;
  background-color: #1e847f;
  height: 600px;
  margin: 1px;
  overflow: auto;
}
</style>
