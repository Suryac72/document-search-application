export interface Document {
  documentId?: string;
  documentTitle?: string;
  documentName?: string;
  documentContent?: string;
  documentScore?: number;
}

export interface SearchBar {
  query: string;
  setQuery: (query: string) => void;
  onSearch: () => void;
  isLoading: boolean;
}

export interface DocumentDashboard {
  document: Document;
}

export interface ErrorMessage {
  errorMessage: string;
}

export interface Search {
  documents: Document[];
  isLoading: boolean;
  isSearched: boolean;
}
