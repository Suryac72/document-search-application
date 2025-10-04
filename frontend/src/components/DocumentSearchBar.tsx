import { Search, Loader2 } from "lucide-react";
import type { SearchBar as SearchBarProps } from "../models/types";

/**
 * Search Bar Component --> It Contains input with search button, which triggers search api
 */
export const DocumentSearchBar = ({
  query,
  setQuery,
  onSearch,
  isLoading,
}: SearchBarProps) => {

  /**
   * This method triggers search call on key press "Enter button"
   * @param event 
   */
  const handleKeyPress = (event: React.KeyboardEvent<HTMLInputElement>) => {
    if (event.key === "Enter") {
      onSearch();
    }
  };

  return (
    <div className="bg-white rounded-lg shadow-lg p-6">
      <div className="flex gap-3">
        <div className="flex-1 relative">
          <input
            type="text"
            value={query}
            onChange={(e) => setQuery(e.target.value)}
            onKeyDown={handleKeyPress}
            placeholder="Enter your search query..."
            className="w-full px-4 py-3 border-2 border-gray-300 rounded-lg focus:border-indigo-500 focus:outline-none transition-colors"
          />
        </div>
        <button
          onClick={onSearch}
          disabled={isLoading}
          className="px-6 py-3 bg-indigo-600 text-white rounded-lg hover:bg-indigo-700 disabled:bg-gray-400 disabled:cursor-not-allowed transition-colors flex items-center gap-2 font-medium"
        >
          {isLoading ? (
            <>
              <Loader2 className="w-5 h-5 animate-spin" />
              Searching...
            </>
          ) : (
            <>
              <Search className="w-5 h-5" />
              Search
            </>
          )}
        </button>
      </div>
    </div>
  );
};
