package fileio;

public final class ActionsFiltersInput {
    private ActionsFiltersSortInput sort;
    private ActionsFiltersContainsInput contains;

    public ActionsFiltersInput() {
    }

    public ActionsFiltersSortInput getSort() {
        return this.sort;
    }

    public void setSort(final ActionsFiltersSortInput sort) {
        this.sort = sort;
    }

    public ActionsFiltersContainsInput getContains() {
        return this.contains;
    }

    public void setContains(final ActionsFiltersContainsInput contains) {
        this.contains = contains;
    }
}
