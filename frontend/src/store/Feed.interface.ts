// api data
export interface Category {
  categoryId: number;
  categoryName: string;
}
// api data
export interface Rss {
  rssId: number;
  rssUrl: string;
  category: Category;
  rssName: string | null;
}
// api data
export interface SubscribeList {
  subscribeId: number;
  subscribeName: string;
  feedId: number;
  userNo: number;
  rss: Rss;
}
// api data
export interface FeedList {
  feedId: number;
  userNo: number;
  feedName: string;
  subscribeList?: SubscribeList[];
}

// api data
export interface Feed {
  feedId: number;
  subscribeId: number;
  subscribeName: string;
  userNo: number;
  rss: Rss;
}

export interface Context {
  showCtx: boolean;
  x: number;
  y: number;
}

export interface Board {
  boardId: number;
  userNo: number;
  boardName: string;
  newsList: [];
}

export interface FeedModule {
  rssList: Rss[];
  feedList: FeedList[];
  boardList: Board[];
  article: Article | null;
  feed: FeedList | null;
  board: Board | null;
  subscribeId: number | null;
  articleList: Article[];
  subsContextMenu: Context;
  feedContextMenu: Context;
  boardContextMenu: Context;
}

// dummy
export interface Article {
  title: string;
  description: string;
  link: string;
  pubDate: string;
  rssTitle: string;
}
