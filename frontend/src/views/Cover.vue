<template>
  <div class="container mt-5">
    <div class="row py-5">
      <ServiceDescription />
      <RssImage />
    </div>
    <hr />
    <ServiceStep />
    <hr />
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

<style></style>
