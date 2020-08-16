<template>
  <div class="container mt-5">
    <v-text-field
      label="제목"
      single-line
      v-model="title"
      class="font-weight-bold font-size: 3rem"
    ></v-text-field>

    <v-combobox
      v-model="nowTagList"
      hint="최대 5개까지 설정 가능합니다."
      label="#을 제외한 태그 이름을 작성해주세요."
      multiple
      small-chips
    >
    </v-combobox>

    <div class="editor">
      <editor-menu-bar :editor="editor" v-slot="{ commands, isActive }">
        <div class="menubar">
          <button
            class="menubar__button"
            :class="{ 'is-active': isActive.bold() }"
            @click="commands.bold"
          >
            <img class="icon" src="@/assets/tiptap/icons/bold.svg" />
          </button>

          <button
            class="menubar__button"
            :class="{ 'is-active': isActive.italic() }"
            @click="commands.italic"
          >
            <img class="icon" src="@/assets/tiptap/icons/italic.svg" />
          </button>

          <button
            class="menubar__button"
            :class="{ 'is-active': isActive.strike() }"
            @click="commands.strike"
          >
            <img class="icon" src="@/assets/tiptap/icons/strike.svg" />
          </button>

          <button
            class="menubar__button"
            :class="{ 'is-active': isActive.underline() }"
            @click="commands.underline"
          >
            <img class="icon" src="@/assets/tiptap/icons/underline.svg" />
          </button>

          <button
            class="menubar__button"
            :class="{ 'is-active': isActive.code() }"
            @click="commands.code"
          >
            <img class="icon" src="@/assets/tiptap/icons/code.svg" />
          </button>

          <button
            class="menubar__button"
            :class="{ 'is-active': isActive.paragraph() }"
            @click="commands.paragraph"
          >
            <img class="icon" src="@/assets/tiptap/icons/paragraph.svg" />
          </button>

          <button
            class="menubar__button"
            :class="{ 'is-active': isActive.heading({ level: 1 }) }"
            @click="commands.heading({ level: 1 })"
          >
            H1
          </button>

          <button
            class="menubar__button"
            :class="{ 'is-active': isActive.heading({ level: 2 }) }"
            @click="commands.heading({ level: 2 })"
          >
            H2
          </button>

          <button
            class="menubar__button"
            :class="{ 'is-active': isActive.heading({ level: 3 }) }"
            @click="commands.heading({ level: 3 })"
          >
            H3
          </button>

          <button
            class="menubar__button"
            :class="{ 'is-active': isActive.bullet_list() }"
            @click="commands.bullet_list"
          >
            <img class="icon" src="@/assets/tiptap/icons/ul.svg" />
          </button>

          <button
            class="menubar__button"
            :class="{ 'is-active': isActive.ordered_list() }"
            @click="commands.ordered_list"
          >
            <img class="icon" src="@/assets/tiptap/icons/ol.svg" />
          </button>

          <button
            class="menubar__button"
            :class="{ 'is-active': isActive.blockquote() }"
            @click="commands.blockquote"
          >
            <img class="icon" src="@/assets/tiptap/icons/quote.svg" />
          </button>

          <button
            class="menubar__button"
            :class="{ 'is-active': isActive.code_block() }"
            @click="commands.code_block"
          >
            <img class="icon" src="@/assets/tiptap/icons/code.svg" />
          </button>

          <button class="menubar__button" @click="commands.horizontal_rule">
            <img class="icon" src="@/assets/tiptap/icons/hr.svg" />
          </button>

          <button class="menubar__button" @click="commands.undo">
            <img class="icon" src="@/assets/tiptap/icons/undo.svg" />
          </button>

          <button class="menubar__button" @click="commands.redo">
            <img class="icon" src="@/assets/tiptap/icons/redo.svg" />
          </button>
        </div>
      </editor-menu-bar>

      <editor-menu-bubble
        :editor="editor"
        :keep-in-bounds="keepInBounds"
        v-slot="{ commands, isActive, menu }"
      >
        <div
          class="menububble"
          :class="{ 'is-active': menu.isActive }"
          :style="`left: ${menu.left}px; bottom: ${menu.bottom}px;`"
        >
          <button
            class="menububble__button"
            :class="{ 'is-active': isActive.bold() }"
            @click="commands.bold"
          >
            <img class="icon" src="@/assets/tiptap/icons/bold.svg" />
          </button>

          <button
            class="menububble__button"
            :class="{ 'is-active': isActive.italic() }"
            @click="commands.italic"
          >
            <img class="icon" src="@/assets/tiptap/icons/italic.svg" />
          </button>

          <button
            class="menububble__button"
            :class="{ 'is-active': isActive.code() }"
            @click="commands.code"
          >
            <img class="icon" src="@/assets/tiptap/icons/code.svg" />
          </button>
        </div>
      </editor-menu-bubble>

      <editor-content class="content" :editor="editor" />
      <!-- <v-textarea name id cols="30" rows="10" :editor="editor"></v-textarea> -->

      <v-flex offset-lg10 lg2>
        <v-btn small outlined color="secondary" class="mt-10" @click="addPost">
          <v-icon left>mdi-plus</v-icon>SAVE
        </v-btn>
        <v-snackbar v-model="snackbar" timeout="2000">
          게시글이 저장되었습니다.

          <template v-slot:action="{ attrs }">
            <v-btn color="pink" text v-bind="attrs" @click="snackbar = false">
              Close
            </v-btn>
          </template>
        </v-snackbar>
      </v-flex>
    </div>
  </div>
</template>

<script>
import { Component, Vue, Watch } from "vue-property-decorator";
import { namespace } from "vuex-class";
import { Editor, EditorContent, EditorMenuBar, EditorMenuBubble } from "tiptap";

import {
  Blockquote,
  CodeBlock,
  HardBreak,
  Heading,
  HorizontalRule,
  OrderedList,
  BulletList,
  ListItem,
  TodoItem,
  TodoList,
  Bold,
  Code,
  Italic,
  Link,
  Strike,
  Underline,
  History
} from "tiptap-extensions";

const mypageModule = namespace("mypageModule");

@Component({
  components: {
    EditorContent,
    EditorMenuBar,
    EditorMenuBubble
  }
})
export default class EditArticle extends Vue {
  @mypageModule.State postId;
  @mypageModule.State post;
  @mypageModule.Mutation SELECT_POST;
  @mypageModule.Action FETCH_POST;
  @mypageModule.Action ADD_POST;
  @mypageModule.Action UPDATE_POST;

  title = "";
  snackbar = false;
  nowTagList = [];

  search = null;

  setContent() {
    this.editor.setContent(
      {
        type: "doc",
        content: []
      },
      true
    );
    this.editor.focus();
  }

  @Watch("post")
  setTitle() {
    this.title = this.post.postTitle;
    this.html = this.post.postContent;
    this.nowTagList = [];
    this.post.tagList.forEach(element => {
      this.nowTagList.push(element.tagName);
    });
    this.editor.setContent(this.post.postContent);
  }

  @Watch("nowTagList")
  maxTag() {
    if (this.nowTagList.length > 5) {
      this.$nextTick(() => this.nowTagList.pop());
    }
  }

  data() {
    return {
      keepInBounds: true,
      editor: new Editor({
        extensions: [
          new Blockquote(),
          new BulletList(),
          new CodeBlock(),
          new HardBreak(),
          new Heading({ levels: [1, 2, 3] }),
          new HorizontalRule(),
          new ListItem(),
          new OrderedList(),
          new TodoItem(),
          new TodoList(),
          new Link(),
          new Bold(),
          new Code(),
          new Italic(),
          new Strike(),
          new Underline(),
          new History()
        ],
        content: `
        `,
        onUpdate: ({ getJSON, getHTML }) => {
          this.json = getJSON();
          this.html = getHTML();
        }
      }),
      json: "",
      html: ""
    };
  }

  addPost() {
    if (isNaN(this.postId)) {
      const submitTagList = [];
      this.nowTagList.forEach(element => {
        const oneTagList = {
          tagName: element
        };
        submitTagList.push(oneTagList);
      });
      console.log(submitTagList);
      this.ADD_POST({
        postContent: this.html,
        postDirId: this.$route.params.postDirId,
        postTitle: this.title,
        tagList: submitTagList
      });
      this.snackbar = true;
    } else {
      this.UPDATE_POST({
        postContent: this.html,
        postDirId: this.$route.params.postDirId || this.post.postDirId,
        postId: Number(this.$route.params.postId),
        postTitle: this.title,
        tagList: this.nowTagList
      });
      this.snackbar = true;
    }
    this.$emit("save");
  }

  @Watch("$route", { immediate: true })
  selectPost() {
    this.title = "";
    this.html = "";
    this.nowTagList = [];
    this.SELECT_POST({
      postId: Number(this.$route.params.postId)
    });

    if (
      ["NewScrapInFeed", "NewScrapInSubs", "NewScrapInBoard"].includes(
        this.$route.name
      ) &&
      document.querySelector(".content")
    ) {
      const content = document.querySelector(".content");
      content.querySelector("p").innerText = "";
    }
  }

  mounted() {
    if ("NewScrapFromGoole" === this.$route.name) {
      this.editor.setContent(
        {
          type: "doc",
          content: [
            {
              type: "paragraph",
              content: [
                {
                  type: "text",
                  text: localStorage.getItem("scrapData")
                }
              ]
            }
          ]
        },
        true
      );

      this.editor.focus();
    }
  }

  @Watch("postId", { immediate: true })
  fetchPost() {
    if (!isNaN(this.postId)) {
      this.FETCH_POST(this.postId);
    }
  }
}
</script>
<style lang="scss" scope>
.icon {
  position: relative;
  display: inline-block;
  vertical-align: middle;
  width: 0.8rem;
  height: 0.8rem;
  margin: 0 0.3rem;
  top: -0.05rem;
  fill: currentColor;

  // &.has-align-fix {
  // 	top: -.1rem;
  // }

  &__svg {
    display: inline-block;
    vertical-align: top;
    width: 100%;
    height: 100%;
  }

  &:first-child {
    margin-left: 0;
  }

  &:last-child {
    margin-right: 0;
  }
}

// svg sprite
body > svg,
.icon use > svg,
symbol {
  path,
  rect,
  circle,
  g {
    fill: currentColor;
    stroke: none;
  }

  *[d="M0 0h24v24H0z"] {
    display: none;
  }
}

.menububble {
  background-color: white !important;
}

.content {
  box-shadow: inset 0 1px 2px rgba(10, 10, 10, 0.1);
  border: 1px solid #dbdbdb;
  display: block;
  min-height: 350px;
  max-width: 100%;
  min-width: 100%;
  padding: 0.625em;
  resize: vertical;
  background-color: white;
  border-radius: 4px;
}
</style>
