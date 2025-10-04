import { FileText } from "lucide-react";

/**
 * 
 * @returns Empty Component --> Renders when no results found from api call
 */
export const Empty = () => {
  return (
    <div className="text-center py-12">
      <FileText className="w-16 h-16 text-gray-300 mx-auto mb-4" />
      <p className="text-gray-500 text-lg">No Document Found</p>
      <p className="text-gray-400 text-sm mt-2">Try with different search</p>
    </div>
  );
};
