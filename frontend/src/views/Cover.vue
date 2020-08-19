<template>
  <div>
    <div class="back row py-5">
      <ServiceDescription />
    </div>
    <ServiceStep />
    <UserInformation />
  </div>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
import ServiceDescription from "@/components/cover/ServiceDescription.vue";
import RssImage from "@/components/cover/RssImage.vue";
import UserInformation from "@/components/cover/UserInformation.vue";
import ServiceStep from "@/components/cover/ServiceStep.vue";

@Component({
  components: {
    ServiceDescription,
    RssImage,
    UserInformation,
    ServiceStep
  }
})
export default class Cover extends Vue {}
</script>

<style>
.back {
  background-size: 100% 100%;
  background-color: #1e847f;
  height: 500px;
  margin: 1px;
  overflow: auto;
}
</style>
