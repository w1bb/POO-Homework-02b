package execution.pages;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

public abstract class Page {
    protected String name;
    protected ArrayList<String> visitablePages;
    protected ObjectMapper objectMapper;

    public Page(final String name, final ArrayList<String> visitablePages) {
        this.name = name;
        this.visitablePages = visitablePages;
        this.objectMapper = new ObjectMapper();
    }

    /**
     * This method returns true if a page with a given name can be navigated to from the current
     * page.
     * @param searchName The name of the page to be checked.
     * @return true if and only if a page can be navigated.
     */
    public final boolean canVisit(final String searchName) {
        return visitablePages.contains(searchName);
    }

    /**
     * This method executes a feature on the current page.
     * @param pq The structure containing relevant information for the current request.
     * @return A PageResponse object containing useful information about the request.
     */
    public abstract PageResponse execute(PageQuery pq);

    /**
     * This method executes after a given page was just visited.
     * @param pq The structure containing relevant information for the current request.
     * @return A PageResponse object containing useful information about the request.
     */
    public abstract PageResponse afterEnter(PageQuery pq);
}
