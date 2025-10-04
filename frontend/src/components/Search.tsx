import type { Search as SearchProps } from "../models/types";
import { DocumentDashboard } from "./DocumentDashboard";
import { Empty } from "./Empty";
export const Search = ({ documents, isLoading, isSearched }: SearchProps) => {
  if (!isSearched || isLoading) {
    return null;
  }

  return (
    <div className="bg-white rounded-lg shadow-lg p-6">
      <h2 className="text-xl font-semibold text-gray-800 mb-4">
        Search Results ({documents.length})
      </h2>

      {documents.length === 0 ? (
        <Empty />
      ) : (
        <div className="space-y-4">
          {documents.map((document, documentIndex) => (
            <DocumentDashboard key={document.documentId || documentIndex} document={document} />
          ))}
        </div>
      )}
    </div>
  );
};
