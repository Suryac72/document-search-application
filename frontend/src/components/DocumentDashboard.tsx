import { FileText } from "lucide-react";
import type { DocumentDashboard as DocumentDashboardProps } from "../models/types";
/**
 * Document Dashboard Componet --> Displays all search results 
 */
export const DocumentDashboard = ({ document }: DocumentDashboardProps) => {
  return (
    <div className="border border-gray-200 rounded-lg p-4 hover:border-indigo-300 hover:shadow-md transition-all">
      <div className="flex items-start gap-3">
        <FileText className="w-5 h-5 text-indigo-600 mt-1 flex-shrink-0" />
        <div className="flex-1">
          <h3 className="font-semibold text-gray-800 mb-1">
            {document.documentTitle ||
              document.documentName ||
              "Untitled Document"}
          </h3>
          {document.documentContent && (
            <p className="text-gray-600 text-sm line-clamp-2">
              {document.documentContent}
            </p>
          )}
          {document.documentScore && (
            <div className="mt-2">
              <span className="text-xs text-gray-500">
                Relevance: {(document.documentScore * 100).toFixed(1)}%
              </span>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};
