// dummy
export interface SidebarItem {
  id: number;
  title: string;
  url?: string;
}
// dummy
export interface SidebarList {
  title: string;
  icon?: string;
  active?: boolean;
  items?: SidebarItem[];
}
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

export interface FeedModule {
  rssList: Rss[];
  feedList: FeedList[];
  boardList: SidebarList[];
  article: Article | null;
  subscribeId: number | null;
  articleList: Article[];
}
// dummy
export interface RssList {
  id: number;
  title: string;
  url: string;
}
// dummy
export interface Article {
  title: string;
  description: string;
  link: string;
  pubDate: string;
  rssTitle: string;
}
