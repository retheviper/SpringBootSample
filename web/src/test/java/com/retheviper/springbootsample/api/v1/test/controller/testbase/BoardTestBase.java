package com.retheviper.springbootsample.api.v1.test.controller.testbase;

import com.retheviper.springbootsample.api.v1.controller.board.ArticleApiController;
import com.retheviper.springbootsample.api.v1.controller.board.BoardApiController;
import com.retheviper.springbootsample.api.v1.controller.board.CategoryApiController;
import com.retheviper.springbootsample.api.v1.form.board.ArticleForm;
import com.retheviper.springbootsample.api.v1.form.board.BoardForm;
import com.retheviper.springbootsample.api.v1.form.board.CategoryForm;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.context.TestComponent;

@TestComponent
@RequiredArgsConstructor
public class BoardTestBase {

    private final BoardApiController boardController;

    private final CategoryApiController categoryController;

    private final ArticleApiController articleController;

    public long initBoard() {
        final BoardForm form = new BoardForm();
        form.setName("TestBoard");
        form.setDescription("TestDescription");
        return this.boardController.createBoard(form).getId();
    }

    public void finalizeBoard(final long id) {
        this.boardController.deleteBoard(id);
    }

    public long initCategory(final long boardId) {
        final CategoryForm form = new CategoryForm();
        form.setName("TestCategory");
        return this.categoryController.createCategory(boardId, form).getId();
    }

    public void finalizeCategory(final long id) {
        this.categoryController.deleteCategory(id);
    }

    public long initArticle(final long boardId, final long categoryId) {
        final ArticleForm form = new ArticleForm();
        form.setCategoryId(categoryId);
        form.setTitle("TestTitle");
        form.setContent("TestContent");
        return this.articleController.createArticle(boardId, form).getId();
    }


    public void finalizeArticle(final long id) {
        this.articleController.deleteArticle(id);
    }
}
