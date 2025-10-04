import { useState } from "react";
import { DocumentSearchBar } from "./DocumentSearchBar";
import { Navbar } from "./Navbar";
import { Search } from "./Search";
import { DocumentError } from "./DocumentError";
import type { Document } from "../models/types";

export const Home = () => {
  /**
   * State variables for created for handling component states based on API call
   */
  const [query, setQuery] = useState<string>("");
  const [documents, setDocuments] = useState<Document[]>([]);
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<string | null>(null);
  const [searched, setSearched] = useState<boolean>(false);

  /**
   * Method to search documents from /api/search api
   * @returns 
   */
  const handleSearch = async (): Promise<void> => {
    if (!query.trim()) {
      setError("Please enter a search query");
      return;
    }

    setLoading(true);
    setError(null);
    setSearched(true);

    try {
      const response = await fetch(
        `/api/search?q=${encodeURIComponent(query)}`
      );

      if (!response.ok) {
        throw new Error("Search failed");
      }

      const data = await response.json();
      const documents = data.hits.map(
        (hit: { document: Document; score: number }) => ({
          ...hit.document,
          documentScore: hit.score,
        })
      );
      setDocuments(documents || data || []);
    } catch (err) {
      setError("Failed to fetch documents");
      setDocuments([]);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-100 p-8">
      <div className="max-w-4xl mx-auto">
        <Navbar />

        <div className="mb-8">
          <DocumentSearchBar
            query={query}
            setQuery={setQuery}
            onSearch={handleSearch}
            isLoading={loading}
          />
        </div>

        {error && <DocumentError errorMessage={error} />}

        <Search
          documents={documents}
          isLoading={loading}
          isSearched={searched}
        />
      </div>
    </div>
  );
};
