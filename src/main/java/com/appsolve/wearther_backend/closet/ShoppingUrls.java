package com.appsolve.wearther_backend.closet;

import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingUrls {
    public static final Map<Long, List<Pair<String, String>>> UPPER_WEAR_INFO = new HashMap<>() {{

        put(1L, List.of(Pair.of("https://zigzag.kr/search?keyword=%EB%AF%BC%EC%86%8C%EB%A7%A4", "지그재그"), // 민소매
                Pair.of("https://m.a-bly.com/screens?screen_name=SUB_CATEGORY_DEPARTMENT&next_token=eyJsIjogIkRlcGFydG1lbnRDYXRlZ29yeVJlYWx0aW1lUmFua0dlbmVyYXRvciIsICJwIjogeyJkZXBhcnRtZW50X3R5cGUiOiAiQ0FURUdPUlkiLCAiY2F0ZWdvcnlfc25vIjogMjF9LCAiZCI6ICJDQVRFR09SWSIsICJwcmV2aW91c19zY3JlZW5fbmFtZSI6ICJDQVRFR09SWV9ERVBBUlRNRU5UIiwgImNhdGVnb3J5X3NubyI6IDIxfQ%3D%3D", "에이블리"),
                Pair.of("https://www.musinsa.com/category/001011?gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EB%AF%BC%EC%86%8C%EB%A7%A4&sort=RECOMMEND&defaultSort=RECOMMEND&sortOrder=DESC&page=1", "29cm")));

        put(2L, List.of(Pair.of("https://zigzag.kr/categories/-1?title=%EC%9D%98%EB%A5%98&category_id=-1&middle_category_id=474&sub_category_id=2791", "지그재그"), // 반소매
                Pair.of("https://m.a-bly.com/screens?screen_name=SUB_CATEGORY_DEPARTMENT&next_token=eyJsIjogIkRlcGFydG1lbnRDYXRlZ29yeVJlYWx0aW1lUmFua0dlbmVyYXRvciIsICJwIjogeyJkZXBhcnRtZW50X3R5cGUiOiAiQ0FURUdPUlkiLCAiY2F0ZWdvcnlfc25vIjogMjF9LCAiZCI6ICJDQVRFR09SWSIsICJwcmV2aW91c19zY3JlZW5fbmFtZSI6ICJDQVRFR09SWV9ERVBBUlRNRU5UIiwgImNhdGVnb3J5X3NubyI6IDIxfQ%3D%3D&filter_sub_category_sno=18", "에이블리"),
                Pair.of("https://www.musinsa.com/category/001001?gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EB%B0%98%EC%86%8C%EB%A7%A4&sort=RECOMMEND&defaultSort=RECOMMEND&sortOrder=DESC&page=1", "29cm")));

        put(3L, List.of(Pair.of("https://zigzag.kr/search?keyword=%EB%A7%A8%ED%88%AC%EB%A7%A8%20%ED%9B%84%EB%93%9C%ED%8B%B0", "지그재그"), // 맨투맨, 후드티
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%EB%A7%A8%ED%88%AC%EB%A7%A8%20%ED%9B%84%EB%93%9C%ED%8B%B0&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/search/goods?keyword=%EB%A7%A8%ED%88%AC%EB%A7%A8+%ED%9B%84%EB%93%9C&gf=A&isOriginal=true", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EB%A7%A8%ED%88%AC%EB%A7%A8+%ED%9B%84%EB%93%9C&sort=RECOMMEND&sortOrder=DESC&defaultSort=RECOMMEND&keywordTypes=category", "29cm")));

        put(4L, List.of(Pair.of("https://zigzag.kr/search?keyword=%EC%85%94%EC%B8%A0%20%EB%B8%94%EB%9D%BC%EC%9A%B0%EC%8A%A4", "지그재그"), // 셔츠, 블라우스
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%EC%85%94%EC%B8%A0%20%EB%B8%94%EB%9D%BC%EC%9A%B0%EC%8A%A4&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/category/001002?gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EC%85%94%EC%B8%A0+%EB%B8%94%EB%9D%BC%EC%9A%B0%EC%8A%A4&sort=RECOMMEND&sortOrder=DESC&defaultSort=RECOMMEND&keywordTypes=category", "29cm")));

        put(5L, List.of(Pair.of("https://zigzag.kr/categories/-1?title=%EC%9D%98%EB%A5%98&category_id=-1&middle_category_id=474&sub_category_id=482", "지그재그"), // 니트
                Pair.of("https://m.a-bly.com/screens?screen_name=SUB_CATEGORY_DEPARTMENT&next_token=eyJsIjogIkRlcGFydG1lbnRDYXRlZ29yeVJlYWx0aW1lUmFua0dlbmVyYXRvciIsICJwIjogeyJkZXBhcnRtZW50X3R5cGUiOiAiQ0FURUdPUlkiLCAiY2F0ZWdvcnlfc25vIjogMjk5fSwgImQiOiAiQ0FURUdPUlkiLCAicHJldmlvdXNfc2NyZWVuX25hbWUiOiAiQ0FURUdPUllfREVQQVJUTUVOVCIsICJjYXRlZ29yeV9zbm8iOiAyOTl9", "에이블리"),
                Pair.of("https://www.musinsa.com/category/001006?gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EB%8B%88%ED%8A%B8&sort=RECOMMEND&defaultSort=RECOMMEND&sortOrder=DESC&keywordTypes=category", "29cm")));

        put(6L, List.of(Pair.of("https://zigzag.kr/search?keyword=%EC%98%A4%ED%94%84%EC%88%84%EB%8D%94", "지그재그"), // 오프숄더
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%EC%98%A4%ED%94%84%EC%88%84%EB%8D%94&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/search/goods?keyword=%EC%98%A4%ED%94%84%EC%88%84%EB%8D%94&keywordType=keyword&gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EC%98%A4%ED%94%84%EC%88%84%EB%8D%94&sort=RECOMMEND&sortOrder=DESC&defaultSort=RECOMMEND&keywordTypes=", "29cm")));

        put(7L, List.of(Pair.of("https://zigzag.kr/search?keyword=%ED%9E%88%ED%8A%B8%ED%83%9D", "지그재그"), // 히트택
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%ED%9E%88%ED%8A%B8%ED%83%9D&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/search/goods?keyword=%ED%9E%88%ED%8A%B8%ED%83%9D&keywordType=keyword&gf=A", "무신사")));

        put(8L, List.of(Pair.of("https://zigzag.kr/search?keyword=%EA%B8%B0%EB%AA%A8", "지그재그"), // 기모
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%EA%B8%B0%EB%AA%A8&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/search/goods?keyword=%EA%B8%B0%EB%AA%A8&keywordType=keyword&gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EA%B8%B0%EB%AA%A8&sort=RECOMMEND&sortOrder=DESC&defaultSort=RECOMMEND&keywordTypes=", "29cm")));

        put(9L, List.of(Pair.of("https://zigzag.kr/categories/-1?title=%EC%95%84%EC%9A%B0%ED%84%B0&category_id=-1&middle_category_id=436&sub_category_id=437", "지그재그"), // 가디건
                Pair.of("https://m.a-bly.com/screens?screen_name=SUB_CATEGORY_DEPARTMENT&next_token=eyJsIjogIkRlcGFydG1lbnRDYXRlZ29yeVJlYWx0aW1lUmFua0dlbmVyYXRvciIsICJwIjogeyJkZXBhcnRtZW50X3R5cGUiOiAiQ0FURUdPUlkiLCAiY2F0ZWdvcnlfc25vIjogMTZ9LCAiZCI6ICJDQVRFR09SWSIsICJwcmV2aW91c19zY3JlZW5fbmFtZSI6ICJPVkVSVklFVyIsICJjYXRlZ29yeV9zbm8iOiAxNn0%3D", "에이블리"),
                Pair.of("https://www.musinsa.com/category/002020?gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EA%B0%80%EB%94%94%EA%B1%B4&sort=RECOMMEND&sortOrder=DESC&defaultSort=RECOMMEND&keywordTypes=category", "29cm")));

        put(10L, List.of(Pair.of("https://zigzag.kr/categories/-1?title=%EC%95%84%EC%9A%B0%ED%84%B0&category_id=-1&middle_category_id=436&sub_category_id=460", "지그재그"), // 사파리자켓
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%EC%82%AC%ED%8C%8C%EB%A6%AC%EC%9E%90%EC%BC%93&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/category/002014?gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EC%82%AC%ED%8C%8C%EB%A6%AC%EC%9E%90%EC%BC%93&sort=RECOMMEND&defaultSort=RECOMMEND&sortOrder=DESC&keywordTypes=category", "29cm")));

        put(11L, List.of(Pair.of("https://zigzag.kr/search?keyword=%ED%8A%B8%EC%9C%84%EB%93%9C%EC%9E%90%EC%BC%93", "지그재그"), // 트위드 자켓
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%ED%8A%B8%EC%9C%84%EB%93%9C%EC%9E%90%EC%BC%93&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/search/goods?keyword=%ED%8A%B8%EC%9C%84%EB%93%9C+%EC%9E%90%EC%BC%93&keywordType=keyword&gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%ED%8A%B8%EC%9C%84%EB%93%9C%EC%9E%90%EC%BC%93&sort=RECOMMEND&sortOrder=DESC&defaultSort=RECOMMEND&keywordTypes=", "29cm")));

        put(12L, List.of(Pair.of("https://zigzag.kr/categories/-1?title=%EC%95%84%EC%9A%B0%ED%84%B0&category_id=-1&middle_category_id=436&sub_category_id=442", "지그재그"), // 레더 자켓
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%EB%A0%88%EB%8D%94%EC%9E%90%EC%BC%93&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/category/002002?gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EB%A0%88%EB%8D%94%EC%9E%90%EC%BC%93&sort=RECOMMEND&sortOrder=DESC&defaultSort=RECOMMEND&keywordTypes=category", "29cm")));

        put(13L, List.of(Pair.of("https://zigzag.kr/categories/-1?title=%EC%95%84%EC%9A%B0%ED%84%B0&category_id=-1&middle_category_id=436&sub_category_id=447", "지그재그"), // 트렌치코트
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%ED%8A%B8%EB%A0%8C%EC%B9%98%EC%BD%94%ED%8A%B8&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/category/002?keyword=%ED%8A%B8%EB%A0%8C%EC%B9%98%EC%BD%94%ED%8A%B8&keywordType=keyword&gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%ED%8A%B8%EB%A0%8C%EC%B9%98%EC%BD%94%ED%8A%B8&sort=RECOMMEND&sortOrder=DESC&defaultSort=RECOMMEND&page=1", "29cm")));

        put(14L, List.of(Pair.of("https://zigzag.kr/search?keyword=%EC%88%8F%EC%BD%94%ED%8A%B8", "지그재그"), // 숏코트
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%EC%88%8F%EC%BD%94%ED%8A%B8&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/category/002?keyword=%EC%88%8F%EC%BD%94%ED%8A%B8&keywordType=keyword&gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EC%88%8F%EC%BD%94%ED%8A%B8&sort=RECOMMEND&sortOrder=DESC&defaultSort=RECOMMEND&page=1", "29cm")));

        put(15L, List.of(Pair.of("https://zigzag.kr/search?keyword=%EB%AC%B4%EC%8A%A4%ED%83%95", "지그재그"), // 무스탕
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%EB%AC%B4%EC%8A%A4%ED%83%95&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/category/002025?gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EB%AC%B4%EC%8A%A4%ED%83%95&sort=RECOMMEND&sortOrder=DESC&defaultSort=RECOMMEND&keywordTypes=category", "29cm")));

        put(16L, List.of(Pair.of("https://zigzag.kr/search?keyword=%EA%B2%BD%EB%9F%89%20%ED%8C%A8%EB%94%A9", "지그재그"), // 경량 패딩
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%EA%B2%BD%EB%9F%89%20%ED%8C%A8%EB%94%A9&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/search/goods?keyword=%EA%B2%BD%EB%9F%89+%ED%8C%A8%EB%94%A9&keywordType=keyword&gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EA%B2%BD%EB%9F%89+%ED%8C%A8%EB%94%A9&sort=RECOMMEND&sortOrder=DESC&defaultSort=RECOMMEND&keywordTypes=brand", "29cm")));

        put(17L, List.of(Pair.of("https://zigzag.kr/categories/-1?title=%EC%95%84%EC%9A%B0%ED%84%B0&category_id=-1&middle_category_id=436&sub_category_id=464", "지그재그"), // 롱패딩
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%EB%A1%B1%ED%8C%A8%EB%94%A9&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/search/goods?keyword=%EB%A1%B1%ED%8C%A8%EB%94%A9&keywordType=keyword&gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EB%A1%B1%ED%8C%A8%EB%94%A9&sort=RECOMMEND&sortOrder=DESC&defaultSort=RECOMMEND&keywordTypes=category", "29cm")));

        put(18L, List.of(Pair.of("https://zigzag.kr/categories/-1?title=%EC%95%84%EC%9A%B0%ED%84%B0&category_id=-1&middle_category_id=436&sub_category_id=463", "지그재그"), // 숏패딩
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%EC%88%8F%ED%8C%A8%EB%94%A9&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/search/goods?keyword=%EC%88%8F%ED%8C%A8%EB%94%A9&keywordType=keyword&gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EC%88%8F%ED%8C%A8%EB%94%A9&sort=RECOMMEND&sortOrder=DESC&defaultSort=RECOMMEND&keywordTypes=category", "29cm")));

        put(19L, List.of(Pair.of("https://zigzag.kr/categories/-1?title=%EC%95%84%EC%9A%B0%ED%84%B0&category_id=-1&middle_category_id=436&sub_category_id=454", "지그재그"), // 점퍼
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%EC%A0%90%ED%8D%BC&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/search/goods?keyword=%EC%A0%90%ED%8D%BC&keywordType=keyword&gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EC%A0%90%ED%8D%BC&sort=RECOMMEND&sortOrder=DESC&defaultSort=RECOMMEND&keywordTypes=category", "29cm")));

        put(20L, List.of(Pair.of("https://zigzag.kr/search?keyword=%ED%9B%84%EB%93%9C%EC%A7%91%EC%97%85", "지그재그"), // 후드집업
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%ED%9B%84%EB%93%9C%EC%A7%91%EC%97%85&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/search/goods?keyword=%ED%9B%84%EB%93%9C%EC%A7%91%EC%97%85&keywordType=keyword&gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%ED%9B%84%EB%93%9C%EC%A7%91%EC%97%85&sort=RECOMMEND&defaultSort=RECOMMEND&sortOrder=DESC&keywordTypes=category", "29cm")));

        put(21L, List.of(Pair.of("https://zigzag.kr/search?keyword=%EB%B0%94%EB%9E%8C%EB%A7%89%EC%9D%B4", "지그재그"), // 바람막이
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%EB%B0%94%EB%9E%8C%EB%A7%89%EC%9D%B4&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/search/goods?keyword=%EB%B0%94%EB%9E%8C%EB%A7%89%EC%9D%B4&keywordType=keyword&gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EB%B0%94%EB%9E%8C%EB%A7%89%EC%9D%B4&sort=RECOMMEND&sortOrder=DESC&defaultSort=RECOMMEND&keywordTypes=category", "29cm")));
    }};

    public static final Map<Long, List<Pair<String, String>>> LOWER_WEAR_INFO = new HashMap<>() {{
        put(1L, List.of(Pair.of("https://zigzag.kr/categories/-1?title=%EC%9D%98%EB%A5%98&category_id=-1&middle_category_id=547&sub_category_id=550", "지그재그"), // 숏팬츠
                Pair.of("https://m.a-bly.com/screens?screen_name=SUB_CATEGORY_DEPARTMENT&next_token=eyJsIjogIkRlcGFydG1lbnRDYXRlZ29yeVJlYWx0aW1lUmFua0dlbmVyYXRvciIsICJwIjogeyJkZXBhcnRtZW50X3R5cGUiOiAiQ0FURUdPUlkiLCAiY2F0ZWdvcnlfc25vIjogMTc3fSwgImQiOiAiQ0FURUdPUlkiLCAicHJldmlvdXNfc2NyZWVuX25hbWUiOiAiT1ZFUlZJRVciLCAiY2F0ZWdvcnlfc25vIjogMTc3fQ%3D%3D", "에이블리"),
                Pair.of("https://www.musinsa.com/category/003009?gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/category/list?categoryLargeCode=268100100&categoryMediumCode=268106100&sort=RECOMMEND&defaultSort=RECOMMEND&sortOrder=DESC&page=1&categorySmallCode=268106108", "29cm")));

        put(2L, List.of(Pair.of("https://zigzag.kr/categories/-1?title=%EC%9D%98%EB%A5%98&category_id=-1&middle_category_id=547&sub_category_id=549", "지그재그"), // 슬랙스
                Pair.of("https://m.a-bly.com/screens?screen_name=SUB_CATEGORY_DEPARTMENT&next_token=eyJsIjogIkRlcGFydG1lbnRDYXRlZ29yeVJlYWx0aW1lUmFua0dlbmVyYXRvciIsICJwIjogeyJkZXBhcnRtZW50X3R5cGUiOiAiQ0FURUdPUlkiLCAiY2F0ZWdvcnlfc25vIjogMTc4fSwgImQiOiAiQ0FURUdPUlkiLCAicHJldmlvdXNfc2NyZWVuX25hbWUiOiAiT1ZFUlZJRVciLCAiY2F0ZWdvcnlfc25vIjogMTc4fQ%3D%3D", "에이블리"),
                Pair.of("https://www.musinsa.com/category/003008?gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/category/list?categoryLargeCode=268100100&categoryMediumCode=268106100&sort=RECOMMEND&defaultSort=RECOMMEND&sortOrder=DESC&page=1&categorySmallCode=268106106", "29cm")));

        put(3L, List.of(Pair.of("https://zigzag.kr/categories/-1?title=%EC%9D%98%EB%A5%98&category_id=-1&middle_category_id=547&sub_category_id=2796", "지그재그"), // 데님
                Pair.of("https://m.a-bly.com/screens?screen_name=SUB_CATEGORY_DEPARTMENT&next_token=eyJsIjogIkRlcGFydG1lbnRDYXRlZ29yeVJlYWx0aW1lUmFua0dlbmVyYXRvciIsICJwIjogeyJkZXBhcnRtZW50X3R5cGUiOiAiQ0FURUdPUlkiLCAiY2F0ZWdvcnlfc25vIjogMTc4fSwgImQiOiAiQ0FURUdPUlkiLCAicHJldmlvdXNfc2NyZWVuX25hbWUiOiAiT1ZFUlZJRVciLCAiY2F0ZWdvcnlfc25vIjogMTc4fQ%3D%3D&filter_sub_category_sno=501", "에이블리"),
                Pair.of("https://www.musinsa.com/category/003002?gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/category/list?categoryLargeCode=268100100&categoryMediumCode=268106100&sort=RECOMMEND&defaultSort=RECOMMEND&sortOrder=DESC&page=1&categorySmallCode=268106104", "29cm")));

        put(4L, List.of(Pair.of("https://zigzag.kr/categories/-1?title=%EC%9D%98%EB%A5%98&category_id=-1&middle_category_id=547&sub_category_id=551", "지그재그"), // 와이드팬츠
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%EC%99%80%EC%9D%B4%EB%93%9C%ED%8C%AC%EC%B8%A0&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/category/003?keyword=%EC%99%80%EC%9D%B4%EB%93%9C%ED%8C%AC%EC%B8%A0&keywordType=keyword&gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/category/list?categoryLargeCode=268100100&categoryMediumCode=268106100&sort=RECOMMEND&defaultSort=RECOMMEND&sortOrder=DESC&page=1&categorySmallCode=268106102", "29cm")));

        put(5L, List.of(Pair.of("https://zigzag.kr/categories/-1?title=%EC%9D%98%EB%A5%98&category_id=-1&middle_category_id=547&sub_category_id=552", "지그재그"), // 스키니
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%EC%8A%A4%ED%82%A4%EB%8B%88&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/category/003?keyword=%EC%8A%A4%ED%82%A4%EB%8B%88&keywordType=keyword&gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EC%8A%A4%ED%82%A4%EB%8B%88&sort=RECOMMEND&defaultSort=RECOMMEND&sortOrder=DESC&page=1", "29cm")));

        put(6L, List.of(Pair.of("https://zigzag.kr/categories/-1?title=%EC%9D%98%EB%A5%98&category_id=-1&middle_category_id=547&sub_category_id=553", "지그재그"), // 부츠컷
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%EB%B6%80%EC%B8%A0%EC%BB%B7&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/category/003?keyword=%EB%B6%80%EC%B8%A0%EC%BB%B7&keywordType=keyword&gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/category/list?categoryLargeCode=268100100&categoryMediumCode=268106100&sort=RECOMMEND&defaultSort=RECOMMEND&sortOrder=DESC&page=1&categorySmallCode=268106110", "29cm")));

        put(7L, List.of(Pair.of("https://zigzag.kr/categories/-1?title=%EC%9D%98%EB%A5%98&category_id=-1&middle_category_id=547&sub_category_id=554", "지그재그"), // 조거팬츠
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%EC%A1%B0%EA%B1%B0%ED%8C%AC%EC%B8%A0&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/category/003004?gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EC%A1%B0%EA%B1%B0%ED%8C%AC%EC%B8%A0&sort=RECOMMEND&defaultSort=RECOMMEND&sortOrder=DESC&page=1", "29cm")));

        put(8L, List.of(Pair.of("https://zigzag.kr/categories/-1?title=%EC%9D%98%EB%A5%98&category_id=-1&middle_category_id=547&sub_category_id=556", "지그재그"), // 점프수트
                Pair.of("https://m.a-bly.com/screens?screen_name=SUB_CATEGORY_DEPARTMENT&next_token=eyJsIjogIkRlcGFydG1lbnRDYXRlZ29yeVJlYWx0aW1lUmFua0dlbmVyYXRvciIsICJwIjogeyJkZXBhcnRtZW50X3R5cGUiOiAiQ0FURUdPUlkiLCAiY2F0ZWdvcnlfc25vIjogNTMzfSwgImQiOiAiQ0FURUdPUlkiLCAicHJldmlvdXNfc2NyZWVuX25hbWUiOiAiT1ZFUlZJRVciLCAiY2F0ZWdvcnlfc25vIjogNTMzfQ%3D%3D", "에이블리"),
                Pair.of("https://www.musinsa.com/category/003010?gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/category/list?categoryLargeCode=268100100&categoryMediumCode=268115100&sort=RECOMMEND&defaultSort=RECOMMEND&sortOrder=DESC&page=1", "29cm")));

        put(9L, List.of(Pair.of("https://zigzag.kr/search?keyword=%20%EB%B0%98%EB%B0%94%EC%A7%80", "지그재그"), // 반바지
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%EB%B0%98%EB%B0%94%EC%A7%80&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/category/003?keyword=%EB%B0%98%EB%B0%94%EC%A7%80&keywordType=keyword&gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EB%B0%98%EB%B0%94%EC%A7%80&sort=RECOMMEND&defaultSort=RECOMMEND&sortOrder=DESC&page=1", "29cm")));

        put(10L, List.of(Pair.of("https://zigzag.kr/search?keyword=%EA%B8%B0%EB%AA%A8%EB%B0%94%EC%A7%80", "지그재그"), // 기모바지
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%EA%B8%B0%EB%AA%A8%EB%B0%94%EC%A7%80&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/category/003?keyword=%EA%B8%B0%EB%AA%A8%EB%B0%94%EC%A7%80&keywordType=keyword&gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EA%B8%B0%EB%AA%A8%EB%B0%94%EC%A7%80&sort=RECOMMEND&defaultSort=RECOMMEND&sortOrder=DESC&page=1", "29cm")));

        put(11L, List.of(Pair.of("https://zigzag.kr/categories/-1?title=%EC%9D%98%EB%A5%98&category_id=-1&middle_category_id=560&sub_category_id=561", "지그재그"), // 미니 스커트
                Pair.of("https://m.a-bly.com/screens?screen_name=SUB_CATEGORY_DEPARTMENT&next_token=eyJsIjogIkRlcGFydG1lbnRDYXRlZ29yeVJlYWx0aW1lUmFua0dlbmVyYXRvciIsICJwIjogeyJkZXBhcnRtZW50X3R5cGUiOiAiQ0FURUdPUlkiLCAiY2F0ZWdvcnlfc25vIjogMjA0fSwgImQiOiAiQ0FURUdPUlkiLCAicHJldmlvdXNfc2NyZWVuX25hbWUiOiAiT1ZFUlZJRVciLCAiY2F0ZWdvcnlfc25vIjogMjA0fQ%3D%3D", "에이블리"),
                Pair.of("https://www.musinsa.com/category/100004?gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/category/list?categoryLargeCode=268100100&categoryMediumCode=268107100&sort=RECOMMEND&defaultSort=RECOMMEND&sortOrder=DESC&page=1&categorySmallCode=268107101", "29cm")));

        put(12L, List.of(Pair.of("https://zigzag.kr/categories/-1?title=%EC%9D%98%EB%A5%98&category_id=-1&middle_category_id=560&sub_category_id=568", "지그재그"), // 미디 스커트
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%EB%AF%B8%EB%94%94%20%EC%8A%A4%EC%BB%A4%ED%8A%B8&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/category/100005?gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/category/list?categoryLargeCode=268100100&categoryMediumCode=268107100&sort=RECOMMEND&defaultSort=RECOMMEND&sortOrder=DESC&page=1&categorySmallCode=268107102", "29cm")));

        put(13L, List.of(Pair.of("https://zigzag.kr/categories/-1?title=%EC%9D%98%EB%A5%98&category_id=-1&middle_category_id=560&sub_category_id=575", "지그재그"), // 롱 스커트
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%EB%A1%B1%20%EC%8A%A4%EC%BB%A4%ED%8A%B8&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/category/100006?gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/category/list?categoryLargeCode=268100100&categoryMediumCode=268107100&sort=RECOMMEND&defaultSort=RECOMMEND&sortOrder=DESC&page=1&categorySmallCode=268107103", "29cm")));
    }};

    public static final Map<Long, List<Pair<String, String>>> OTHER_WEAR_INFO = new HashMap<>() {{
        put(1L, List.of(Pair.of("https://zigzag.kr/categories/582?title=%EC%8A%AC%EB%A6%AC%ED%8D%BC/%EC%AA%BC%EB%A6%AC&category_id=582&middle_category_id=621", "지그재그"), // 쪼리
                Pair.of("https://m.a-bly.com/screens?screen_name=SUB_CATEGORY_DEPARTMENT&next_token=eyJsIjogIkRlcGFydG1lbnRDYXRlZ29yeVJlYWx0aW1lUmFua0dlbmVyYXRvciIsICJwIjogeyJkZXBhcnRtZW50X3R5cGUiOiAiQ0FURUdPUlkiLCAiY2F0ZWdvcnlfc25vIjogNDkyfSwgImQiOiAiQ0FURUdPUlkiLCAicHJldmlvdXNfc2NyZWVuX25hbWUiOiAiQ0FURUdPUllfREVQQVJUTUVOVCIsICJjYXRlZ29yeV9zbm8iOiA0OTJ9", "에이블리"),
                Pair.of("https://www.musinsa.com/search/goods?keyword=%EC%AA%BC%EB%A6%AC&keywordType=keyword&gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EC%AA%BC%EB%A6%AC&sort=RECOMMEND&defaultSort=RECOMMEND&sortOrder=DESC&page=1", "29cm")));

        put(2L, List.of(Pair.of("https://zigzag.kr/search?keyword=%EB%AA%A9%EB%8F%84%EB%A6%AC%20%EC%9E%A5%EA%B0%91", "지그재그"), // 목도리, 장갑
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%EB%AA%A9%EB%8F%84%EB%A6%AC%20%EC%9E%A5%EA%B0%91&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/search/goods?keyword=%EB%AA%A9%EB%8F%84%EB%A6%AC+%EC%9E%A5%EA%B0%91&keywordType=keyword&gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EB%AA%A9%EB%8F%84%EB%A6%AC+%EC%9E%A5%EA%B0%91&sort=RECOMMEND&defaultSort=RECOMMEND&sortOrder=DESC&page=1", "29cm")));

        put(3L, List.of(Pair.of("https://zigzag.kr/search?keyword=%EC%96%B4%EA%B7%B8%20%EA%B7%80%EB%A7%88%EA%B0%9C", "지그재그"), // 어그, 귀마개
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%EC%96%B4%EA%B7%B8%20%EA%B7%80%EB%A7%88%EA%B0%9C&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/search/goods?keyword=%EC%96%B4%EA%B7%B8+%EA%B7%80%EB%A7%88%EA%B0%9C&keywordType=keyword&gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EC%96%B4%EA%B7%B8+%EA%B7%80%EB%A7%88%EA%B0%9C&sort=RECOMMEND&defaultSort=RECOMMEND&sortOrder=DESC&page=1", "29cm")));

        put(4L, List.of(Pair.of("https://zigzag.kr/search?keyword=%EC%9A%B0%EC%82%B0", "지그재그"), // 우산
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%EC%9A%B0%EC%82%B0&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/search/goods?keyword=%EC%9A%B0%EC%82%B0&keywordType=keyword&gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EC%9A%B0%EC%82%B0&sort=RECOMMEND&sortOrder=DESC&defaultSort=RECOMMEND&keywordTypes=category", "29cm")));

        put(5L, List.of(Pair.of("https://zigzag.kr/search?keyword=%EC%9E%A5%ED%99%94", "지그재그"), // 장화
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%EC%9E%A5%ED%99%94&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/search/goods?keyword=%EC%9E%A5%ED%99%94&keywordType=keyword&gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EC%9E%A5%ED%99%94&sort=RECOMMEND&sortOrder=DESC&defaultSort=RECOMMEND&page=1", "29cm")));
        
        put(6L, List.of(Pair.of("https://zigzag.kr/search?keyword=%EC%96%91%EC%82%B0", "지그재그"), // 양산
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%EC%96%91%EC%82%B0&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/search/goods?keyword=%EC%96%91%EC%82%B0&keywordType=keyword&gf=A&suggestKeyword=%EC%96%91%EB%A7%90", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EC%96%91%EC%82%B0&sort=RECOMMEND&sortOrder=DESC&defaultSort=RECOMMEND&keywordTypes=", "29cm")));

        put(7L, List.of(Pair.of("https://zigzag.kr/search?keyword=%EB%B9%84%EB%8B%88%20%EB%B2%99%EA%B1%B0%EC%A7%80", "지그재그"), // 비니, 벙거지
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%EB%B9%84%EB%8B%88%20%EB%B2%99%EA%B1%B0%EC%A7%80&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/search/goods?keyword=%EB%B9%84%EB%8B%88+%EB%B2%99%EA%B1%B0%EC%A7%80&keywordType=keyword&gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EB%B9%84%EB%8B%88+%EB%B2%99%EA%B1%B0%EC%A7%80&sort=RECOMMEND&sortOrder=DESC&defaultSort=RECOMMEND&keywordTypes=category", "29cm")));

        put(8L, List.of(Pair.of("https://zigzag.kr/search?keyword=%EB%B3%BC%EC%BA%A1", "지그재그"), // 볼캡
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%EB%B3%BC%EC%BA%A1&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/search/goods?keyword=%EB%B3%BC%EC%BA%A1&keywordType=keyword&gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EB%B3%BC%EC%BA%A1&sort=RECOMMEND&sortOrder=DESC&defaultSort=RECOMMEND&keywordTypes=category", "29cm")));

        put(9L, List.of(Pair.of("https://zigzag.kr/search?keyword=%EC%84%A0%EA%B8%80%EB%9D%BC%EC%8A%A4", "지그재그"), // 선글라스
                Pair.of("https://m.a-bly.com/search?screen_name=SEARCH_RESULT&keyword=%EC%84%A0%EA%B8%80%EB%9D%BC%EC%8A%A4&search_type=DIRECT", "에이블리"),
                Pair.of("https://www.musinsa.com/search/goods?keyword=%EC%84%A0%EA%B8%80%EB%9D%BC%EC%8A%A4&keywordType=keyword&gf=A", "무신사"),
                Pair.of("https://shop.29cm.co.kr/search?keyword=%EC%84%A0%EA%B8%80%EB%9D%BC%EC%8A%A4&sort=RECOMMEND&sortOrder=DESC&defaultSort=RECOMMEND&page=1", "29cm")));
    }};
}